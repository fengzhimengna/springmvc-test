package com.fzm.security.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fzm.security.realm.StatelessToken;
import com.fzm.security.util.Constants;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zhongyuwen on 2016/11/10.
 */
public class StatelessAuthFilter extends AccessControlFilter {

    private Logger logger = LoggerFactory.getLogger(StatelessAuthFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //1、客户端生成的消息摘要
        String clientDigest = request.getParameter(Constants.STATELESS_PARAM_DIGEST);
        //2、客户端传入的用户身份
        long employeeid;
        try{
            employeeid = Long.valueOf(request.getParameter(Constants.STATELESS_PARAM_USERNAME));
        }catch (Throwable throwable){
            logger.error("请求中未找到用户信息！"+request.getParameterMap());
            return false;
        }
        //3、客户端请求的参数列表
        Map<String, String[]> params =
                new LinkedHashMap<>(request.getParameterMap());
        params.remove(Constants.STATELESS_PARAM_DIGEST);
        logger.info("Filter收到param:");
        params.forEach((key,value)-> logger.info(key+"-"+ Arrays.toString(value)));
        //4、生成无状态Token
        StatelessToken token = new StatelessToken(employeeid, params, clientDigest);
        try {
            //5、委托给Realm进行登录
            Subject subject = getSubject(request, response);
            subject.login(token);
            logger.info("验证成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("验证失败！-",e);
            onLoginFail(response); //6、登录失败
            return false;
        }
        return true;
    }
    //登录失败时默认返回401状态码
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("login error");
    }
}
package com.fzm.security.realm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fzm.security.util.HmacSHA256Utils;

public class StatelessRealm extends AuthorizingRealm {

   private Logger logger = LoggerFactory.getLogger(StatelessRealm.class);

   @Override
   public boolean supports(AuthenticationToken token) {
      //仅支持StatelessToken类型的Token
      return token instanceof StatelessToken;
   }

   @SuppressWarnings("unchecked")
   protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
      SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//      authorizationInfo.setRoles(wsService.findEmployeeRoles(employeeid));
//      authorizationInfo.setStringPermissions(wsService.findEmployeePerms(employeeid));
      List<String> roles = new ArrayList<String>();
      roles.add("admin");
      authorizationInfo.setRoles((Set<String>) roles);
      List<String> stringPermissions = new ArrayList<String>();
      stringPermissions.add("/home.do");
      authorizationInfo.setStringPermissions((Set<String>) stringPermissions);
      return authorizationInfo;
   }
   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
      StatelessToken statelessToken = (StatelessToken) token;
      //验证用户以及账套
//      if(user == null || wsUser == null) {
//         throw new UnknownAccountException();//没找到帐号
//      }
//    if(user.getUsestatus() != Constants.DDL_USESTATUS_YES
//          || wsUser.getUsestatus() != Constants.DDL_USESTATUS_YES) {
//       throw new LockedAccountException(); //帐号锁定
//    }
      //验证摘要信息
      String serverDigetst = HmacSHA256Utils.digest("",statelessToken.getParams());
      logger.info(serverDigetst);
      return new SimpleAuthenticationInfo(
            1, //用户名
            serverDigetst,
            getName()  //realm name
      );
   }
}
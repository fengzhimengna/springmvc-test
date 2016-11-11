package com.fzm.security.realm;

import org.apache.shiro.authc.AuthenticationToken;

import java.util.Map;

/**
 * Created by zhongyuwen on 2016/11/10.
 */
public class StatelessToken implements AuthenticationToken {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object principal;
    private Map<String, ?> params;
    private String clientDigest;

    public StatelessToken(long employeeid, Map<String, ?> params, String clientDigest) {
        this.principal = employeeid;
        this.params = params;
        this.clientDigest = clientDigest;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    public Map<String, ?> getParams() {
        return params;
    }

    public void setParams(Map<String, ?> params) {
        this.params = params;
    }

    public String getClientDigest() {
        return clientDigest;
    }

    public void setClientDigest(String clientDigest) {
        this.clientDigest = clientDigest;
    }

    @Override
    public Object getPrincipal() {  return principal;}
    @Override
    public Object getCredentials() {  return clientDigest;}

    @Override
    public String toString() {
        return "StatelessToken{" +
                "principal=" + principal +
                ", params=" + params +
                ", clientDigest='" + clientDigest + '\'' +
                '}';
    }
}
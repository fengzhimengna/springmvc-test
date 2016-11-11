package com.fzm.security.util;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.shiro.codec.Hex;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongyuwen on 2016/11/10.
 */
public class HmacSHA256Utils {

    @SuppressWarnings("static-access")
	public static String digest(String key, String content) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            byte[] secretByte = key.getBytes(Constants.SYSTEM_ENCODING);
            byte[] dataBytes = content.getBytes(Constants.SYSTEM_ENCODING);

            SecretKey secret = new SecretKeySpec(secretByte, "HMACSHA256");
            mac.init(secret);

            byte[] doFinal = mac.doFinal(dataBytes);
            char[] hexC = new Hex().encode(doFinal);
            byte[] hexB = hexC.toString().getBytes();
            return new String(hexB, Constants.SYSTEM_ENCODING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
	public static String digest(String key, Map<String, ?> map) {
        StringBuilder s = new StringBuilder();
        for(Object values : map.values()) {
            if(values instanceof String[]) {
                for(String value : (String[])values) {
                    s.append(value);
                }
            } else if(values instanceof List) {
                for(String value : (List<String>)values) {
                    s.append(value);
                }
            } else {
                s.append(values);
            }
        }
        return digest(key, s.toString());
    }

}
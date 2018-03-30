package com.lyu.csdndemo.common.util.user;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class UserInfo {

    /**
     * 实现对密码加密
     *
     * @param password
     * @return
     */
    public static String encode(String password) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.encodePassword(password, "lyu");
    }


}

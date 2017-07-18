package cn.ffcs.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量
 * @author ScienJus
 * @date 2015/7/31.
 */
public class Constants {

    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 72;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";
    /**
     * 允许不鉴权的URL
     */
    public static final List<String> ALLOWURL = new ArrayList<String>(){{
        add("/tokens");
        add("/error");
    }};


}

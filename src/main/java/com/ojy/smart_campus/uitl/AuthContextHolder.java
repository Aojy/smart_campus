package com.ojy.smart_campus.uitl;

import javax.servlet.http.HttpServletRequest;

public class AuthContextHolder {

    /**
     * 从请求头token中获取userId
     * @param request
     * @return
     */
     public static Long getUserIdToken(HttpServletRequest request) {
         // 从请求头中获取token
         String token = request.getHeader("token");
         // 调用工具类获取userId
         Long userId = JwtHelper.getUserId(token);
         return userId;
     }

    /**
     * 从请求头token获取userName
     * @param request
     * @return
     */
     public static String getUserName(HttpServletRequest request) {
         return JwtHelper.getUserName(request.getHeader("token"));
     }
}

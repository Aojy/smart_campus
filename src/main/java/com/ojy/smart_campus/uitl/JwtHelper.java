package com.ojy.smart_campus.uitl;



import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;
import java.util.Date;

public class JwtHelper {
    private static long tokenExpiration = 24 * 60 * 60 * 1000;

    private static String tokenSignKey = "123456";

    /**
     * 生成token字符串
     * @param userId
     * @param userType
     * @return
     */
    public static String createToken(Long userId, Integer userType) {
        String token = Jwts.builder()
                .setSubject("YYGH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("userType", userType)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    /**
     * 从token字符串获取userid
     * @param token
     * @return
     */
    public static Long getUserId(String token) {
        if (StringUtils.isEmpty(token)) {return null;}
        return ((Integer) getClaims(token).get(("userId"))).longValue();
    }

    /**
     * 从token字符串获取userType
     * @param token
     * @return
     */
    public static Integer getUserType(String token) {
        if (StringUtils.isEmpty(token)) {return null;}
        return (Integer) getClaims(token).get(("userType"));
    }

    /**
     * 从token字符串获取userName
     * @param token
     * @return
     */
    public static String getUserName(String token) {
        if (StringUtils.isEmpty(token)) {return "";}
        return getClaims(token).get(("userName")) + "";
    }

    /**
     * 判断token是否有效
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        try {
            boolean isExpire = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration()
                    .before(new Date());
            // 没过期，返回false
            return isExpire;
        } catch (Exception e) {
            // 已过期，返回true
            return true;
        }
    }

    /**
     * 刷新Token
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        String refreshedToken = null;
        try {
            final Claims claims = Jwts
                    .parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody();
            refreshedToken = JwtHelper.createToken(getUserId(token), getUserType(token));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return refreshedToken;
    }

    private static Claims getClaims(String token) {
        Jws<Claims> claimsJws =
                Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        return claimsJws.getBody();
    }
}

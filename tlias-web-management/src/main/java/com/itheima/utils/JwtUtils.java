package com.itheima.utils; // 记得修改成你自己的包名

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // 1. 签名密钥 (随便写，但要保密，且在HS256算法下长度最好长一点)
    private static String signKey = "itheima_tlias_web_management_system_secret_key";
    
    // 2. 过期时间 (这里设定为 12 小时，单位毫秒)
    private static Long expire = 43200000L; 

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return 生成的JWT令牌字符串
     */
    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims) // 自定义信息（载荷）
                .signWith(SignatureAlgorithm.HS256, signKey) // 签名算法和密钥
                .setExpiration(new Date(System.currentTimeMillis() + expire)) // 过期时间
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey) // 指定签名密钥
                .parseClaimsJws(jwt) // 开始解析
                .getBody(); // 获取载荷
        return claims;
    }
}
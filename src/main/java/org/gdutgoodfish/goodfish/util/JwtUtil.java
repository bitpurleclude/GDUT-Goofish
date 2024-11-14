package org.gdutgoodfish.goodfish.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.HashSet;
import java.util.Map;
import java.util.Date;
import java.util.Set;

public class JwtUtil {
    private static String signKey = "goodfish"; // 生成令牌的签名密钥
    private static long expireTime = 43200000L; // 令牌存续时间为12小时
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 生成密钥


    private static Set<String> blacklist = new HashSet<>(); // 令牌黑名单
    /**
     * 生成令牌
     * @param claims 令牌中的信息
     * @return 生成的令牌
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims) // 设置令牌中的信息
                .setIssuedAt(new Date(System.currentTimeMillis())) // 设置令牌的签发时间
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 设置令牌的过期时间（10小时）
                .signWith(SECRET_KEY) // 使用生成的密钥进行签名
                .compact();
    }

    /**
     * 解析令牌
     * @param token 令牌
     * @return 令牌中的信息
     */
    public static Claims phaseJwt(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY) // 设置用于验证签名的密钥
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 将令牌加入黑名单
     * @param token 令牌
     */
    public static void addToken(String token) {
        blacklist.add(token);
    }

    /**
     * 判断令牌是否在黑名单中
     * @param token 令牌
     * @return 令牌是否在黑名单中
     */
    public static boolean isTokenBlacklisted(String token) {
        return blacklist.contains(token);
    }

}

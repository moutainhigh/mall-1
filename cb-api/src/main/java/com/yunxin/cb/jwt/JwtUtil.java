package com.yunxin.cb.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;

public class JwtUtil {

    /**
     * Token过期时间
     */
    private static final int tokenExpirationTime = 10080; // 7天

    /**
     * Token签名密钥
     */
    private static final String tokenSigningKey = "yunxin7788";

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static String generateToken(int customerId, String mobile) {
        HashMap<String, Object> map = new HashMap<>();
        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(map)
                .setId(customerId + "")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpirationTime * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, tokenSigningKey);
        jwtBuilder.claim("mobile", mobile);
        return jwtBuilder.compact();
    }

    private static Claims verifyToken(String token) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(tokenSigningKey))
                .parseClaimsJws(token).getBody();
    }


    public static Token getToken(String token) {
        try {
            Claims claims = verifyToken(token);
            return new Token(Integer.parseInt(claims.getId()), (String) claims.get("mobile"));
        } catch (Exception e) {
            throw new TokenValidationException("Wrong token");
        }
    }

    static class TokenValidationException extends RuntimeException {
        TokenValidationException(String msg) {
            super(msg);
        }
    }
}
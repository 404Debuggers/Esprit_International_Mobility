package com.Debuggers.MobiliteInternational.Security.Jwt;

import com.Debuggers.MobiliteInternational.Security.Services.UserDetailsImpl;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import com.auth0.jwt.JWT;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${imobility.jwtSecret}")
    private String jwtSecret;

    @Value("${imobility.jwtExpirationMs}")
    private int jwtExpirationMs;


    private long jwtExpirationTime = 3600000;

    private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);


    private static final byte[] secretBytes = secret.getEncoded();

    private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);


    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, base64SecretBytes)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(base64SecretBytes).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(base64SecretBytes).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }



    public String generateEmailVerificationToken(String publicUserId) {

        String token = Jwts.builder()
                .setSubject(publicUserId)														// token ama userid String
                .setExpiration(new Date(System.currentTimeMillis()+ jwtExpirationTime))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

        return token;
    }

    public String generatePhoneVerificationCode() {

        String chars = "azertyuiopqsdfghjklmwxcvbn1234567890_@";
        StringBuilder paswword = new StringBuilder();
        Random rnd = new Random();
        while (paswword.length() < 5) {
            int index = (int) (rnd.nextFloat() * chars.length());
            paswword.append(chars.charAt(index));
        }

        return paswword.toString();

    }

    public static boolean hastokenExpired2(String token) {
        DecodedJWT jwt = JWT.decode(token);
        if( jwt.getExpiresAt().before(new Date())) {
            return true;
        }
        return false;
    }


}
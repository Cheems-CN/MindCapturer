package org.mindcapture.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JWTUtils {

    private static final String secret = "qwer";

    public static String getJWT(Map<String, Object> claims){
        return JWT.create().
                withClaim("claims", claims).
                withExpiresAt(new Date(System.currentTimeMillis() + 1000*60*60*3)).
                sign(Algorithm.HMAC256(secret));
    }

    public static Map<String, Object> parseJWT(String token){
        return JWT.require(Algorithm.HMAC256(secret)).build().verify(token).getClaims().get("claims").asMap();
    }
}

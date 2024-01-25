package fr.doandgo.gestionrh.utils;

import fr.doandgo.gestionrh.configuration.JWTConfig;
import fr.doandgo.gestionrh.entities.User;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class JWTUtils {

    public static final String JWT_NAME_IN_HTTP_REQUEST = "JWT-TOKEN";

    private final JWTConfig jwtConfig;

    public String buildJWT(User user) {
        String role = user.getRole().toString();
        Map<String, String> map = new HashMap<>();
        map.put("firstname", user.getFirstname());
        map.put("lastname", user.getLastname());
        map.put("password", user.getPassword());
        map.put("email", user.getEmail());
        map.put("roles", role);

        String tokenJWT = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS265")
                .setSubject(String.valueOf(user.getId()))
                .addClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpireIn() * 1000))
                .signWith(jwtConfig.getSecretKey())
                .compact();
        return tokenJWT;
    }



}

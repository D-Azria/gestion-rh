package fr.doandgo.gestionrh.configuration;

import io.jsonwebtoken.security.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Configuration
@Data
public class JWTConfig {

    @Value("${jwt.expires_in}")
    private Long expireIn;
    @Value("${jwt.cookie}")
    private String cookie;
    @Value("${jwt.name}")
    private String name;
    @Value("${jwt.secret}")
    private String secret;

    private Key secretKey;

    @PostConstruct
    public void buildKey() {
        /*
        try {
            secretKey = new SecretKeySpec(Base64.getDecoder().decode(getSecret()), "HmacSHA256");
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

    }

}

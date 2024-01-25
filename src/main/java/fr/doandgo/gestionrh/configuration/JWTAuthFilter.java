package fr.doandgo.gestionrh.configuration;

import fr.doandgo.gestionrh.utils.JWTUtils;
import fr.doandgo.gestionrh.utils.RedisUtils;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthFilter {

    private JWTConfig jwtConfig;
    private JWTUtils jwtUtils;
    private RedisUtils redisUtils;




}

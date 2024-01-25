package fr.doandgo.gestionrh.authentication;

import fr.doandgo.gestionrh.configuration.JWTConfig;
import fr.doandgo.gestionrh.dto.UserDto;
import fr.doandgo.gestionrh.entities.User;
import fr.doandgo.gestionrh.service.UserService;
import fr.doandgo.gestionrh.utils.JWTUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final AuthenticationManager authenticationManager;
    private final JWTConfig jwtConfig;
    private final JWTUtils jwtUtils;

    @Override
    public ResponseEntity<?> getId(HttpHeaders httpHeaders) {
        return null;
    }

    @Override
    public ResponseEntity<?> login(UserDto userDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDto.email(), userDto.password());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authentication)){
            return ResponseEntity.status(403).body("Email or password not corresponding");
        } else {
            AuthenticatedUser authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();
            Map<String, String> map = new HashMap<>();
            String JWToken = jwtUtils.buildJWT(((AuthenticatedUser) authentication.getPrincipal()).getUser());
            map.put(jwtConfig.getName(), JWToken);
            map.put("userId", String.valueOf(authenticatedUser.getUser().getId()));
            return ResponseEntity.status(200).body(map);
        }
    }

    @Override
    public ResponseEntity<?> signout(HttpHeaders httpHeaders) {
        return null;
    }
}

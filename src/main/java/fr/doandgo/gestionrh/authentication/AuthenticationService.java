package fr.doandgo.gestionrh.authentication;

import fr.doandgo.gestionrh.dto.UserDto;
import fr.doandgo.gestionrh.entities.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    ResponseEntity<?> getId(HttpHeaders httpHeaders);

    ResponseEntity<?> login(UserDto userDto);

    ResponseEntity<?> signout(HttpHeaders httpHeaders);

}

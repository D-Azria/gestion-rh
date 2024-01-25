package fr.doandgo.gestionrh.authentication;

import fr.doandgo.gestionrh.entities.User;
import fr.doandgo.gestionrh.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsManager implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> loginUser = userRepository.findByEmail(email);
        try {
            return new AuthenticatedUser(loginUser.get());
        } catch (Exception exception){
            throw new UsernameNotFoundException("No such user !");
        }
    }
}

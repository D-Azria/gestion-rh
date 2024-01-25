package fr.doandgo.gestionrh.serviceImpl;

import fr.doandgo.gestionrh.dto.UserDto;
import fr.doandgo.gestionrh.entities.User;
import fr.doandgo.gestionrh.repository.UserRepository;
import fr.doandgo.gestionrh.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public void create(UserDto userDto) {
        userRepository.save(dtoToEntity(userDto));
    }

    @Override
    public List<UserDto> getAll() {
        return null;
    }

    @Override
    public UserDto getById(Integer jobId) {
        return null;
    }

    @Override
    public User getUserEntityById(Integer userId) {
        return null;
    }

    @Override
    public void update(UserDto userDto) {

    }

    @Override
    public void deleteById(Integer jobId) {

    }

    @Override
    public User dtoToEntity(UserDto userDto) {
        if(userDto.id() == null || userDto.id() == 0){
            return new User(userDto.firstname(),userDto.lastname(), userDto.birthDate(), userDto.email(), userDto.password(), userDto.role());
        } else {
            return new User(userDto.id(), userDto.firstname(), userDto.lastname(), userDto.birthDate(), userDto.email(), userDto.password(), userDto.role());
        }
    }

    @Override
    public UserDto entityToDto(User user) {
        return null;
    }


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

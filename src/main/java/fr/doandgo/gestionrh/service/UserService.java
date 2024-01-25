package fr.doandgo.gestionrh.service;

import fr.doandgo.gestionrh.dto.UserDto;
import fr.doandgo.gestionrh.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface UserService {

    @Transactional
    void create(UserDto userDto);

    List<UserDto> getAll();


    UserDto getById(Integer jobId);

    User getUserEntityById(Integer userId);

    @Transactional
    void update(UserDto userDto);

    @Transactional
    void deleteById(Integer jobId);

    User dtoToEntity(UserDto userDto);

    UserDto entityToDto(User user);
}

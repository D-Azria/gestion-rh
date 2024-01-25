package fr.doandgo.gestionrh.controller;


import fr.doandgo.gestionrh.dto.UserDto;
import fr.doandgo.gestionrh.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController extends AbstractFacadeController<UserDto>{

    private final UserService userService;

    @Override
    public List<UserDto> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<UserDto> getById(Integer id) {
        return null;
    }

    @Override
    public void create(UserDto userDto) {
        userService.create(userDto);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(UserDto userDto) {

    }

    public UserController(UserService userService) {
        this.userService = userService;
    }
}

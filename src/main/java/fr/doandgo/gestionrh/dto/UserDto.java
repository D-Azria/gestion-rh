package fr.doandgo.gestionrh.dto;

import fr.doandgo.gestionrh.enums.AppRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UserDto(Integer id,
                      String firstname,
                      String lastname,
                      Date birthDate,
                      @NotNull @NotBlank String email,
                      @NotNull @NotBlank String password,
                      @NotBlank AppRole role) {
}

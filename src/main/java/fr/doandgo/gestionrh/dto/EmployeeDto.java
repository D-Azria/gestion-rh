package fr.doandgo.gestionrh.dto;

import fr.doandgo.gestionrh.enums.Diplomas;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public record EmployeeDto(Integer id,
                          @NotNull String firstname,
                          @NotNull String lastname,
                          @NotNull Date birthDate,
                          List<Diplomas> diplomas,
                          List<Integer> contractIds,
                          ContractDto contractDto/*,

                          @NotNull Adresse adresse
                          */) {
}

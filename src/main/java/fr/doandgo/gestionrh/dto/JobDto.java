package fr.doandgo.gestionrh.dto;

import fr.doandgo.gestionrh.enums.Category;
import fr.doandgo.gestionrh.enums.Service;
import jakarta.validation.constraints.NotNull;

public record JobDto(Integer id, @NotNull String name,@NotNull  Service service, @NotNull Category category, @NotNull Integer companyId) {
}

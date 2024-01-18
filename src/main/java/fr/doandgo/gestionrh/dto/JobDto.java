package fr.doandgo.gestionrh.dto;

import fr.doandgo.gestionrh.enums.Category;
import fr.doandgo.gestionrh.enums.Service;

public record JobDto(Integer id,
                      String name,
                      Service service,
                      Category category,
                      Integer companyId) {
}

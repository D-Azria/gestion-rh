package fr.doandgo.gestionrh.dto;

import fr.doandgo.gestionrh.entities.Job;
import fr.doandgo.gestionrh.enums.Category;
import fr.doandgo.gestionrh.enums.Service;

import java.util.List;

public record JobDto(Integer id,
                     String name,
                     Service service,
                     Category category,
                     Integer companyId,
                     List<Integer> jobsManagedIds,
                     Integer managerId) {
}

package fr.doandgo.gestionrh.dto;

import fr.doandgo.gestionrh.entities.Job;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CompanyDto(Integer id, @NotNull String name, String url, List<Integer> jobIds) {
}

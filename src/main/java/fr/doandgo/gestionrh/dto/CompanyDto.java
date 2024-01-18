package fr.doandgo.gestionrh.dto;

import jakarta.validation.constraints.NotNull;

public record CompanyDto(Integer id, @NotNull String name, String url) {
}

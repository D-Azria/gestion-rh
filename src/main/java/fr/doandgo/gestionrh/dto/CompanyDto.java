package fr.doandgo.gestionrh.dto;

import jakarta.validation.constraints.NotNull;

public record CompanyDto(@NotNull String name, String url) {
}

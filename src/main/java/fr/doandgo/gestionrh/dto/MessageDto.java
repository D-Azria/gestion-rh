package fr.doandgo.gestionrh.dto;

import jakarta.validation.constraints.NotNull;

public record MessageDto(@NotNull String message) {
}

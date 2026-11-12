package com.gcod.notificaciones.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CrearNotificacionRequest(
        @NotBlank
        @Email
        @Size(max = 254)
        String destinatarioEmail,

        @NotBlank
        @Size(max = 200)
        String asunto,

        @NotBlank
        String contenido,

        @NotBlank
        @Size(max = 30)
        String tipo,

        @Positive
        Long citaId,

        LocalDateTime fechaProgramada
) {
}
package com.gcod.notificaciones.dto;

import java.time.LocalDateTime;

import com.gcod.notificaciones.entity.Notificacion;

public record NotificacionResponse(
        Long id,
        Long citaId,
        String destinatarioEmail,
        String asunto,
        String contenido,
        String tipo,
        String estado,
        LocalDateTime fechaProgramada,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaEnvio
) {
    public static NotificacionResponse desde(Notificacion notificacion) {
        return new NotificacionResponse(
                notificacion.getId(),
                notificacion.getCitaId(),
                notificacion.getDestinatarioEmail(),
                notificacion.getAsunto(),
                notificacion.getContenido(),
                notificacion.getTipo(),
                notificacion.getEstado(),
                notificacion.getFechaProgramada(),
                notificacion.getFechaCreacion(),
                notificacion.getFechaEnvio()
        );
    }
}
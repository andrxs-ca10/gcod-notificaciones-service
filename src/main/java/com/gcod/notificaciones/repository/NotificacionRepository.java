package com.gcod.notificaciones.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcod.notificaciones.entity.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    List<Notificacion> findByEstado(String estado);

    List<Notificacion> findByCitaId(Long citaId);

    List<Notificacion> findByDestinatarioEmailIgnoreCase(String destinatarioEmail);

    List<Notificacion> findByEstadoAndFechaProgramadaLessThanEqual(
            String estado,
            LocalDateTime fechaProgramada
    );
}
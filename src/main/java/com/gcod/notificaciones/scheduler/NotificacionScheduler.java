package com.gcod.notificaciones.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gcod.notificaciones.service.NotificacionService;

@Component
public class NotificacionScheduler {

    private final NotificacionService service;

    public NotificacionScheduler(NotificacionService service) {
        this.service = service;
    }

    @Scheduled(fixedDelayString = "${notificaciones.scheduler.intervalo-ms:60000}")
    public void procesarNotificacionesProgramadas() {
        service.procesarNotificacionesProgramadas();
    }
}
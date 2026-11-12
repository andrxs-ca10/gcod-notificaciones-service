package com.gcod.notificaciones.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionesController {

    @GetMapping("/estado")
    public Map<String, String> consultarEstado() {
        return Map.of(
                "servicio", "gcod-notificaciones-service",
                "estado", "activo"
        );
    }
}
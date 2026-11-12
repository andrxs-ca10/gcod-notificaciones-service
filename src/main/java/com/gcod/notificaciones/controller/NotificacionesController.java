package com.gcod.notificaciones.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcod.notificaciones.dto.CrearNotificacionRequest;
import com.gcod.notificaciones.dto.NotificacionResponse;
import com.gcod.notificaciones.entity.Notificacion;
import com.gcod.notificaciones.service.NotificacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionesController {

    private final NotificacionService service;

    public NotificacionesController(NotificacionService service) {
        this.service = service;
    }

    @GetMapping("/estado")
    public ResponseEntity<String> consultarEstado() {
        return ResponseEntity.ok("gcod-notificaciones-service activo");
    }

    @PostMapping
    public ResponseEntity<NotificacionResponse> crear(
            @Valid @RequestBody CrearNotificacionRequest request) {

        Notificacion notificacion = new Notificacion();
        notificacion.setCitaId(request.citaId());
        notificacion.setDestinatarioEmail(request.destinatarioEmail());
        notificacion.setAsunto(request.asunto());
        notificacion.setContenido(request.contenido());
        notificacion.setTipo(request.tipo());
        notificacion.setFechaProgramada(request.fechaProgramada());

        Notificacion guardada = service.guardar(notificacion);
        URI ubicacion = URI.create("/api/v1/notificaciones/" + guardada.getId());

        return ResponseEntity.created(ubicacion)
                .body(NotificacionResponse.desde(guardada));
    }

    @GetMapping
    public List<NotificacionResponse> listar(
            @RequestParam(required = false) String estado) {

        List<Notificacion> notificaciones =
                estado == null || estado.isBlank()
                        ? service.listarTodas()
                        : service.buscarPorEstado(estado);

        return notificaciones.stream()
                .map(NotificacionResponse::desde)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacionResponse> buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id)
                .map(notificacion ->
                        ResponseEntity.ok(NotificacionResponse.desde(notificacion)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
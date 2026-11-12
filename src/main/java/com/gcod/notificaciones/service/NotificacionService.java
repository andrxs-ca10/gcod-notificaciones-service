package com.gcod.notificaciones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gcod.notificaciones.entity.Notificacion;
import com.gcod.notificaciones.repository.NotificacionRepository;

@Service
public class NotificacionService {

    private final NotificacionRepository repository;

    public NotificacionService(NotificacionRepository repository) {
        this.repository = repository;
    }

    public Notificacion guardar(Notificacion notificacion) {
        return repository.save(notificacion);
    }

    public List<Notificacion> listarTodas() {
        return repository.findAll();
    }

    public Optional<Notificacion> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Notificacion> buscarPorEstado(String estado) {
        return repository.findByEstado(estado);
    }

    public List<Notificacion> buscarPorCita(Long citaId) {
        return repository.findByCitaId(citaId);
    }

    public List<Notificacion> buscarPorDestinatario(String email) {
        return repository.findByDestinatarioEmailIgnoreCase(email);
    }
}
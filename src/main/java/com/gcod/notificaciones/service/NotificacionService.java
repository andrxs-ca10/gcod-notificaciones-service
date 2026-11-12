package com.gcod.notificaciones.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.gcod.notificaciones.entity.Notificacion;
import com.gcod.notificaciones.repository.NotificacionRepository;

@Service
public class NotificacionService {

    private static final Logger logger =
            LoggerFactory.getLogger(NotificacionService.class);

    private final NotificacionRepository repository;
    private final EmailService emailService;

    public NotificacionService(
            NotificacionRepository repository,
            EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
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

    public Optional<Notificacion> enviar(Long id) {
        Optional<Notificacion> resultado = repository.findById(id);

        if (resultado.isEmpty()) {
            return Optional.empty();
        }

        Notificacion notificacion = resultado.get();

        try {
            emailService.enviar(
                    notificacion.getDestinatarioEmail(),
                    notificacion.getAsunto(),
                    notificacion.getContenido()
            );

            notificacion.setEstado("ENVIADA");
            notificacion.setFechaEnvio(LocalDateTime.now());
            notificacion.setErrorEnvio(null);
        } catch (MailException error) {
            notificacion.setEstado("FALLIDA");
            notificacion.setErrorEnvio(
                    "No se pudo enviar el correo. Verifica la configuración SMTP."
            );
            logger.error("Falló el envío de la notificación {}", id, error);
        }

        return Optional.of(repository.save(notificacion));
    }

    public void procesarNotificacionesProgramadas() {
    List<Notificacion> pendientes =
            repository.findByEstadoAndFechaProgramadaLessThanEqual(
                    "PENDIENTE",
                    LocalDateTime.now()
            );

    for (Notificacion notificacion : pendientes) {
        enviar(notificacion.getId());
    }
}

}
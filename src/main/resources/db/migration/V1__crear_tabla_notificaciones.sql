CREATE TABLE notificaciones (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cita_id BIGINT NULL,
    destinatario_email VARCHAR(254) NOT NULL,
    asunto VARCHAR(200) NOT NULL,
    contenido TEXT NOT NULL,
    tipo VARCHAR(30) NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    fecha_programada DATETIME NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_envio DATETIME NULL,
    error_envio VARCHAR(1000) NULL,

    PRIMARY KEY (id),
    INDEX idx_notificaciones_estado_programada (estado, fecha_programada),
    INDEX idx_notificaciones_cita_id (cita_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
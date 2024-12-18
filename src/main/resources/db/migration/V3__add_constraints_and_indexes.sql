-- Agregar restricción única para el correo electrónico en la tabla Usuario
ALTER TABLE usuario ADD CONSTRAINT unique_correo UNIQUE (correo_electronico);

-- Crear índices para mejorar el rendimiento en consultas
CREATE INDEX idx_topico_autor_id ON topico (autor_id);
CREATE INDEX idx_respuesta_topico_id ON respuesta (topico_id);

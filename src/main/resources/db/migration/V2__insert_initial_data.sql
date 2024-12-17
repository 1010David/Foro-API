-- Insertar datos en la tabla Perfil
INSERT INTO perfil (nombre) VALUES ('Administrador'), ('Moderador'), ('Usuario');

-- Insertar un usuario inicial
INSERT INTO usuario (nombre, correo_electronico, contrasena)
VALUES ('admin', 'admin@forohub.com', 'password123');

-- Asociar el usuario inicial con el perfil de Administrador
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (1, 1);

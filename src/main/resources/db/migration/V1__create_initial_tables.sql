-- Tabla Usuario
CREATE TABLE usuario (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  correoelectronico VARCHAR(100) NOT NULL,
  contrasena VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX ui_usuario_nombre (nombre ASC) VISIBLE,
  UNIQUE INDEX ui_usuario_correoelectronico (correoelectronico ASC) VISIBLE
);

-- Tabla Perfil
CREATE TABLE perfil (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX ui_perfil_nombre (nombre ASC) VISIBLE
);

-- Tabla Curso
CREATE TABLE curso (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  categoria VARCHAR(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX ui_curso_nombre (nombre ASC) VISIBLE
);

-- Tabla Topico
CREATE TABLE topico (
  id BIGINT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(100) NOT NULL,
  mensaje VARCHAR(500) NOT NULL,
  fechacreacion DATETIME NOT NULL,
  estado VARCHAR(50) NOT NULL,
  usuario_id BIGINT NOT NULL,
  curso_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX ui_topico_titulo_mensaje (titulo ASC, mensaje ASC) VISIBLE,
  CONSTRAINT fk_topico_usuario_id
    FOREIGN KEY (usuario_id)
    REFERENCES usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_topico_curso_id
    FOREIGN KEY (curso_id)
    REFERENCES curso (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- Tabla intermedia Usuario_Perfil (relación muchos a muchos)
CREATE TABLE perfil_usuario (
  perfil_id BIGINT NOT NULL,
  usuario_id BIGINT NOT NULL,
  PRIMARY KEY (perfil_id, usuario_id),
  CONSTRAINT fk_perfil_usuario_perfil_id
    FOREIGN KEY (perfil_id)
    REFERENCES perfil (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_perfil_usuario_usuario_id
    FOREIGN KEY (usuario_id)
    REFERENCES usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

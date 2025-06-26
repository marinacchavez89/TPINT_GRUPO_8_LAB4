-- Edito la tabla usuario agregando el tipo_usuario
ALTER TABLE usuario
ADD tipo_usuario ENUM('admin', 'cliente') NOT NULL DEFAULT 'cliente';

-- Hacer id_cliente opcional
ALTER TABLE usuario
MODIFY id_cliente INT NULL;

-- Elimino es_admin
ALTER TABLE usuario
DROP COLUMN es_admin;

--insert usuario ADMIN:
INSERT INTO usuario (id_cliente, nombre_usuario, contrasena, estado, tipo_usuario)
VALUES (null, 'admin_user', 'admin123', TRUE, 'admin');
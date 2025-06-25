-- Edito la tabla usuario agregando el tipo_usuario
ALTER TABLE usuario
ADD tipo_usuario ENUM('admin', 'cliente') NOT NULL DEFAULT 'cliente';

-- Hacer id_cliente opcional
ALTER TABLE usuario
MODIFY id_cliente INT NULL;

-- Elimino es_admin
ALTER TABLE usuario
DROP COLUMN es_admin;
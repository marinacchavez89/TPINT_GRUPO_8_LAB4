use banco_db_;

select * from direccion
select * from localidad

-- Direcciones
INSERT INTO direccion (id_localidad, codigo_postal, calle, numero) VALUES
(6, '1900', 'Calle 12', '123'),
(12, '5000', 'Av. Colón', '456'),
(46, '2000', 'Bv. Oroño', '789');

-- Clientes
INSERT INTO cliente (
    dni, cuil, nombre, apellido, sexo, id_nacionalidad,
    fecha_nacimiento, id_direccion, correo_electronico, telefono, estado
) VALUES
('12345678', '20-12345678-3', 'Juan', 'Pérez', 'M', 1, '1985-04-12', 4, 'juanperez@mail.com', '1144445555', true),
('87654321', '27-87654321-9', 'María', 'Gómez', 'F', 1, '1990-09-30', 5, 'mariagomez@mail.com', '1133332222', true),
('11223344', '23-11223344-1', 'Carlos', 'Quispe', 'M', 1, '1988-01-22', 6, 'carlosq@mail.com', '1122221111', true);
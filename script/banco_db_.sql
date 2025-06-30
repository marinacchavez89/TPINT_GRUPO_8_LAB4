create database banco_db_;
use banco_db_;

create table pais_residencia (
    id_pais_residencia INT primary key auto_increment,
    desc_pais_residencia VARCHAR(50) not null unique
);

create table provincia (
    id_provincia INT primary key auto_increment,
    nombre_pcia VARCHAR(50) not null unique,
    id_pais_residencia INT,
    foreign key (id_pais_residencia) references pais_residencia(id_pais_residencia)
);

create table localidad (
    id_localidad INT primary key auto_increment,
    nombre_localidad VARCHAR(50) not null unique,
    id_provincia INT,
    foreign key (id_provincia) references provincia(id_provincia)
);

create table direccion (
    id_direccion INT primary key auto_increment,
    id_localidad INT,
    codigo_postal VARCHAR(10),
    calle VARCHAR(100),
    numero VARCHAR(10),
    foreign key (id_localidad) references localidad(id_localidad)
);

create table nacionalidad (
    id_nacionalidad INT primary key auto_increment,
    desc_nacionalidad VARCHAR(50) not null
);

create table cliente (
    id_cliente INT primary key auto_increment,
    dni VARCHAR(20) not null,
    cuil VARCHAR(20),
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    sexo CHAR(1),
    id_nacionalidad INT,
    fecha_nacimiento DATE,
    id_direccion INT,
    correo_electronico VARCHAR(100),
    telefono VARCHAR(20),
    estado BOOLEAN default true,
    FOREIGN KEY (id_direccion) REFERENCES direccion(id_direccion),
    FOREIGN KEY (id_nacionalidad) REFERENCES nacionalidad(id_nacionalidad),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

create table tipo_cuenta (
    id_tipo_cuenta INT primary key auto_increment,
    desc_tipo_cuenta VARCHAR(50) not null
);

create table cuenta (
    nro_cuenta INT primary key auto_increment,
    id_cliente INT,
    fecha_creacion DATE,
    id_tipo_cuenta INT,
    cbu VARCHAR(30),
    saldo DECIMAL(10,2),
    estado BOOLEAN default true,
    foreign key (id_cliente) references cliente(id_cliente),
    foreign key (id_tipo_cuenta) references tipo_cuenta(id_tipo_cuenta)
);

CREATE TABLE transferencia (
    id_transferencia INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATETIME NOT NULL,
    nro_cuenta_origen INT NOT NULL,
    nro_cuenta_destino INT NOT NULL,
    importe DECIMAL(10,2) NOT NULL CHECK (importe > 0),
    FOREIGN KEY (nro_cuenta_origen) REFERENCES cuenta(nro_cuenta),
    FOREIGN KEY (nro_cuenta_destino) REFERENCES cuenta(nro_cuenta),
    CHECK (nro_cuenta_origen <> nro_cuenta_destino)
);

create table usuario (
    id_usuario INT primary key auto_increment,
    id_cliente INT NULL,
    nombre_usuario VARCHAR(50) not null,
    contrasena VARCHAR(100) not null,
    tipo_usuario ENUM('admin', 'cliente') NOT NULL DEFAULT 'cliente',
    estado BOOLEAN default true,
    foreign key (id_cliente) references cliente(id_cliente)
);

create table prestamo (
    id_prestamo INT primary key auto_increment,
    id_cliente INT,
    nro_cuenta INT,
    fecha_alta DATE,
    importe_pedido DECIMAL(10,2),
    plazo_pago INT,
    importe_a_pagar DECIMAL(10,2),
    cantidad_cuotas INT,
    estado BOOLEAN default false,
    foreign key (id_cliente) references cliente(id_cliente),
    foreign key (nro_cuenta) references cuenta(nro_cuenta)
);

create table cuota (
    id_cuota INT primary key auto_increment,
    id_prestamo INT,
    numero_cuota INT,
    monto DECIMAL(10,2),
    fecha_pago DATE,
    estado BOOLEAN default false,
    foreign key (id_prestamo) references prestamo(id_prestamo)
);

create table tipo_movimiento (
    id_tipo_movimiento INT primary key auto_increment,
    desc_tipo_movimiento VARCHAR(50)
);

create table movimiento (
    id_movimiento INT primary key auto_increment,
    fecha DATE,
    nro_cuenta INT,
    detalle VARCHAR(100),
    importe DECIMAL(10,2),
    id_tipo_movimiento INT,
    foreign key (nro_cuenta) references cuenta(nro_cuenta),
    foreign key (id_tipo_movimiento) references tipo_movimiento(id_tipo_movimiento)
);
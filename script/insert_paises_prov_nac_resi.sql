-- Inserta 5 nacionalidades
INSERT INTO nacionalidad (desc_nacionalidad) VALUES
('Argentina'),
('Chile'),
('Perú'),
('Uruguay'),
('Paraguay');

-- Inserta 5 países
INSERT INTO pais_residencia (desc_pais_residencia) VALUES
('Argentina'),
('Chile'),
('Perú'),
('Uruguay'),
('Paraguay');

-- Inserta 5 provincias para Argentina (ID 1)
INSERT INTO provincia (nombre_pcia, id_pais_residencia) VALUES
('Buenos Aires', 1),
('Córdoba', 1),
('Santa Fe', 1),
('Mendoza', 1),
('Salta', 1);

-- Inserta 5 provincias para Chile (ID 2)
INSERT INTO provincia (nombre_pcia, id_pais_residencia) VALUES
('Santiago Metropolitana', 2),
('Valparaíso', 2),
('Biobío', 2),
('Antofagasta', 2),
('Maule', 2);

-- Inserta 5 provincias para Peru (ID 3)
INSERT INTO provincia (nombre_pcia, id_pais_residencia) VALUES
('Lima', 3),
('Cusco', 3),
('Arequipa', 3),
('La Libertad', 3),
('Piura', 3);

-- Inserta 5 provincias para Uruguay (ID 4)
INSERT INTO provincia (nombre_pcia, id_pais_residencia) VALUES
('Montevideo', 4),
('Canelones', 4),
('Maldonado', 4),
('Colonia', 4),
('Salto', 4);

-- Inserta 5 provincias para Paraguay (ID 5)
INSERT INTO provincia (nombre_pcia, id_pais_residencia) VALUES
('Central', 5),
('Asunción', 5),
('Alto Paraná', 5),
('Itapúa', 5),
('Caaguazú', 5);

-- Buenos Aires (id_provincia=26)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('La Plata', 26),
('Mar del Plata', 26),
('Bahía Blanca', 26),
('Tandil', 26),
('Campana', 26);

-- Córdoba (id_provincia=27)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Córdoba Capital', 27),
('Villa Carlos Paz', 27),
('Río Cuarto', 27),
('Jesús María', 27),
('Alta Gracia', 27);

-- Santa Fe (id_provincia=28)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Rosario', 28),
('Santa Fe Capital', 28),
('Rafaela', 28),
('Venado Tuerto', 28),
('San Lorenzo', 28);

-- Mendoza (id_provincia=29)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Mendoza Capital', 29),
('San Rafael', 29),
('Godoy Cruz', 29),
('Luján de Cuyo', 29),
('Maipú', 29);

-- Salta (id_provincia=30)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Salta Capital', 30),
('Orán', 30),
('Tartagal', 30),
('Metán', 30),
('Cafayate', 30);

-- Santiago Metropolitana (id_provincia = 31)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Santiago', 31),
('Providencia', 31),
('Las Condes', 31),
('Puente Alto', 31),
('Lampa', 31);

-- Valparaíso (id_provincia = 32)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Valparaíso', 32),
('Viña del Mar', 32),
('Quilpué', 32),
('Villa Alemana', 32),
('Concón', 32);

-- Biobío (id_provincia = 33)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Concepción', 33),
('Talcahuano', 33),
('Chiguayante', 33),
('Los Ángeles', 33),
('Hualpén', 33);

-- Antofagasta (id_provincia = 34)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Antofagasta', 34),
('Calama', 34),
('Tocopilla', 34),
('Mejillones', 34),
('Sierra Gorda', 34);

-- Maule (id_provincia = 35)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Talca', 35),
('Curicó', 35),
('Linares', 35),
('Constitución', 35),
('San Javier', 35);

-- Lima (id_provincia = 36)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Lima', 36),
('Callao', 36),
('Miraflores', 36),
('San Isidro', 36),
('Surco', 36);

-- Cusco (id_provincia = 37)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Cusco', 37),
('Urubamba', 37),
('Ollantaytambo', 37),
('Sicuani', 37),
('Paucartambo', 37);

-- Arequipa (id_provincia = 38)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Arequipa', 38),
('Camaná', 38),
('Mollendo', 38),
('Majes', 38),
('Caravelí', 38);

-- La Libertad (id_provincia = 39)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Trujillo', 39),
('Chepén', 39),
('Pacasmayo', 39),
('Ascope', 39),
('Santiago de Chuco', 39);

-- Piura (id_provincia = 40)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Piura', 40),
('Sullana', 40),
('Talara', 40),
('Paita', 40),
('Sechura', 40);

-- Montevideo (id_provincia = 41)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Montevideo', 41),
('Cerro', 41),
('Pocitos', 41),
('Carrasco', 41),
('Punta Carretas', 41);

-- Canelones (id_provincia = 42)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Las Piedras', 42),
('Ciudad de la Costa', 42),
('Canelones', 42),
('Pando', 42),
('Migues', 42);

-- Maldonado (id_provincia = 43)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Punta del Este', 43),
('Maldonado', 43),
('San Carlos', 43),
('Pan de Azúcar', 43),
('La Barra', 43);

-- Colonia (id_provincia = 44)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Colonia del Sacramento', 44),
('Carmelo', 44),
('Nueva Palmira', 44),
('Cardona', 44),
('Juan Lacaze', 44);

-- Salto (id_provincia = 45)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Salto', 45),
('Concordia', 45),
('Arapey', 45),
('Belén', 45),
('Termas del Daymán', 45);

-- Central (id_provincia = 46)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Ñemby', 46),
('Lambaré', 46),
('Fernando de la Mora', 46),
('San Lorenzo', 46),
('Luque', 46);

-- Asunción (id_provincia = 47)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Asunción Centro', 47),
('Villa Morra', 47),
('Recoleta', 47),
('Santísima Trinidad', 47),
('Mariano Roque Alonso', 47);

-- Alto Paraná (id_provincia = 48)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Ciudad del Este', 48),
('Minga Guazú', 48),
('Hernandarias', 48),
('Presidente Franco', 48),
('Santa Rita', 48);

-- Itapúa (id_provincia = 49)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Encarnación', 49),
('Hohenau', 49),
('San Juan Bautista', 49),
('Coronel Bogado', 49),
('Bella Vista', 49);

-- Caaguazú (id_provincia = 50)
INSERT INTO localidad (nombre_localidad, id_provincia) VALUES
('Coronel Oviedo', 50),
('Caaguazú', 50),
('J. Eulogio Estigarribia', 50),
('Tobatí', 50),
('Repatriación', 50);

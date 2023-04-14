DROP DATABASE IF EXISTS centro_medico;
CREATE DATABASE centro_medico;
USE centro_medico;


CREATE TABLE administrador(
id INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
username VARCHAR(50) NOT NULL,
contrasenia VARCHAR(40) NOT NULL,
email VARCHAR (50) NOT NULL,
fecha_nacimiento DATE NOT NULL,
saldo DOUBLE NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE especialidades(
id INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
descripcion VARCHAR(50) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE examenes(
id INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
descripcion VARCHAR(50) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE medicos(
id INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
username VARCHAR(50) NOT NULL,
contrasenia VARCHAR(50) NOT NULL,
direccion VARCHAR(50) NOT NULL,
cui INT NOT NULL,
telefono INT NOT NULL,
email VARCHAR(50) NOT NULL,
fecha_nacimiento DATE NOT NULL,
saldo DOUBLE NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE horarios_medicos(
id_horario INT NOT NULL AUTO_INCREMENT,
id_medico INT NOT NULL,
hora_inicial TIME NOT NULL,
hora_final TIME NOT NULL,
PRIMARY KEY(id_horario),
CONSTRAINT id_medico_horario_fk
FOREIGN KEY(id_medico)
REFERENCES medicos(id)
);

CREATE TABLE especialidades_medicos(
id_especialidad INT NOT NULL,
id_medico INT NOT NULL,
precio DOUBLE NOT NULL,
PRIMARY KEY (id_especialidad,id_medico),
CONSTRAINT id_medico_especialidad_fk
FOREIGN KEY(id_medico)
REFERENCES medicos(id),
CONSTRAINT id_especialidad_medico_fk
FOREIGN KEY(id_especialidad)
REFERENCES especialidades(id)
);

CREATE TABLE laboratorios(
id INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(30) NOT NULL,
username VARCHAR(50) NOT NULL,
contrasenia VARCHAR(50) NOT NULL,
direccion VARCHAR(40) NOT NULL,
cui INT NOT NULL,
telefono INT NOT NULL,
email VARCHAR(40) NOT NULL,
fecha_fundacion DATE NOT NULL,
saldo DOUBLE NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE examenes_laboratorios(
id_laboratorio INT NOT NULL,
id_examen INT NOT NULL,
precio DOUBLE NOT NULL,
PRIMARY KEY (id_laboratorio, id_examen),
CONSTRAINT id_laboratorio_examen_fk
FOREIGN KEY (id_laboratorio)
REFERENCES laboratorios(id),
CONSTRAINT id_examen_laboratorio_fk
FOREIGN KEY (id_examen)
REFERENCES examenes(id)
);
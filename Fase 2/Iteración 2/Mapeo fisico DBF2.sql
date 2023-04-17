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

CREATE TABLE pacientes(
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
ON UPDATE CASCADE
ON DELETE CASCADE
);

CREATE TABLE especialidades_medicos(
id_especialidad INT NOT NULL,
id_medico INT NOT NULL,
precio DOUBLE NOT NULL,
estado VARCHAR(30) NOT NULL,
PRIMARY KEY (id_especialidad,id_medico),
CONSTRAINT id_medico_especialidad_fk
FOREIGN KEY(id_medico)
REFERENCES medicos(id)
ON UPDATE CASCADE
ON DELETE CASCADE,
CONSTRAINT id_especialidad_medico_fk
FOREIGN KEY(id_especialidad)
REFERENCES especialidades(id)
ON UPDATE CASCADE
ON DELETE CASCADE
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
estado VARCHAR(30) NOT NULL,
PRIMARY KEY (id_laboratorio, id_examen),
CONSTRAINT id_laboratorio_examen_fk
FOREIGN KEY (id_laboratorio)
REFERENCES laboratorios(id)
ON UPDATE CASCADE
ON DELETE CASCADE,
CONSTRAINT id_examen_laboratorio_fk
FOREIGN KEY (id_examen)
REFERENCES examenes(id)
ON UPDATE CASCADE
ON DELETE CASCADE
);

CREATE TABLE consultas(
id INT NOT NULL AUTO_INCREMENT,
id_especialidad INT NOT NULL,
id_paciente INT NOT NULL,
id_medico INT NOT NULL,
precio DOUBLE NOT NULL,
porcentaje_app DOUBLE NOT NULL,
fecha_agendada DATETIME NOT NULL,
fecha_creacion DATE NOT NULL,
estado VARCHAR(30) NOT NULL,
informe VARCHAR(50),
PRIMARY KEY (id),
CONSTRAINT id_especialidad_consulta_fk
FOREIGN KEY (id_especialidad)
REFERENCES especialidades(id)
ON DELETE CASCADE
ON UPDATE CASCADE,
CONSTRAINT id_paciente_consulta_fk
FOREIGN KEY (id_paciente)
REFERENCES pacientes(id)
ON DELETE CASCADE
ON UPDATE CASCADE,
CONSTRAINT id_medico_consulta_fk
FOREIGN KEY (id_medico)
REFERENCES medicos(id)
ON DELETE CASCADE
ON UPDATE CASCADE
);

CREATE TABLE examenes_consulta(
id_examen INT NOT NULL,
id_consulta INT NOT NULL,
PRIMARY KEY(id_examen, id_consulta),
CONSTRAINT id_examen_consulta_fk
FOREIGN KEY (id_examen)
REFERENCES examenes(id)
ON DELETE CASCADE
ON UPDATE CASCADE,
CONSTRAINT id_consulta_consultaexamenes_fk
FOREIGN KEY (id_consulta)
REFERENCES consultas(id)
ON DELETE CASCADE
ON UPDATE CASCADE
);

CREATE TABLE solicitudes(
id INT NOT NULL AUTO_INCREMENT,
id_laboratorio INT NOT NULL,
id_paciente INT NOT NULL,
porcentaje_app DOUBLE NOT NULL,
fecha_solicitada DATE NOT NULL,
fecha_finalizada DATE,
estado VARCHAR(30) NOT NULL,
PRIMARY KEY(id),
CONSTRAINT id_laboratorio_solicitud_fk
FOREIGN KEY (id_laboratorio)
REFERENCES laboratorios(id)
ON DELETE CASCADE
ON UPDATE CASCADE,
CONSTRAINT id_paciente_solicitud_fk
FOREIGN KEY (id_paciente)
REFERENCES pacientes(id)
ON DELETE CASCADE
ON UPDATE CASCADE
);

CREATE TABLE examenes_solicitud(
id_solicitud INT NOT NULL,
id_examen INT NOT NULL,
precio INT NOT NULL,
PRIMARY KEY(id_solicitud, id_examen), 
CONSTRAINT id_solicitud_examen_fk
FOREIGN KEY (id_solicitud)
REFERENCES solicitudes(id)
ON DELETE CASCADE
ON UPDATE CASCADE,
CONSTRAINT id_examen_solicitud_fk
FOREIGN KEY (id_examen)
REFERENCES solicitudes(id)
ON DELETE CASCADE
ON UPDATE CASCADE
);

CREATE TABLE cobro_app(
id INT NOT NULL,
fecha_modificacion DATE NOT NULL,
porcentaje DOUBLE NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE recargas(
id INT NOT NULL AUTO_INCREMENT,
id_paciente INT NOT NULL,
recarga INT NOT NULL,
fecha_realizada DATE NOT NULL,
PRIMARY KEY(id, id_paciente),
CONSTRAINT id_paciente_recargas_fk
FOREIGN KEY (id_paciente)
REFERENCES pacientes(id)
ON DELETE CASCADE
ON UPDATE CASCADE
);
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
precio DOUBLE NOT NULL,
PRIMARY KEY(id_solicitud, id_examen), 
CONSTRAINT id_solicitud_examen_fk
FOREIGN KEY (id_solicitud)
REFERENCES solicitudes(id)
ON DELETE CASCADE
ON UPDATE CASCADE,
CONSTRAINT id_examen_solicitud_fk
FOREIGN KEY (id_examen)
REFERENCES examenes(id)
ON DELETE CASCADE
ON UPDATE CASCADE
);

CREATE TABLE cobro_app(
id INT NOT NULL AUTO_INCREMENT,
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


/*usuario por defecto del sistema*/
/*username: userAdmin1, password: admin*/
INSERT INTO administrador VALUES(1000, 'admin1', 'userAdmin1','21232f297a57a5a743894a0e4a801fc3', 'emailAdmin@gmail.com', '2000-01-03', 0);

/*aqui empieza el mapeo físico de la creación del nuevo usuario*/
/*CREATE USER 'proyecto2IPC2_2023'@'localhost' IDENTIFIED BY 'eiler123';*/
GRANT SELECT ON centro_medico.* TO 'proyecto2IPC2_2023'@'localhost';
GRANT DELETE ON centro_medico.* TO 'proyecto2IPC2_2023'@'localhost';
GRANT UPDATE ON centro_medico.* TO 'proyecto2IPC2_2023'@'localhost';
GRANT INSERT ON centro_medico.* TO 'proyecto2IPC2_2023'@'localhost';
/* EL PUERTO TIENE QUE SER: 3306*/

USE centro_medico;

select * from administrador;
select * from especialidades;
select * from examenes;
/*insert into horarios_medicos (id_medico, hora_inicial, hora_final) values(2,'2:00', '4:30');*/
select * from horarios_medicos;
select * from especialidades_medicos;
select * from medicos;
select * from laboratorios;
select * from examenes_laboratorios;
select * from pacientes;
select * from consultas;
select * from examenes_consulta; delete from examenes_consulta where id_examen = 123 and id_consulta=1;
select* from solicitudes;
select * from examenes_solicitud;

update  consultas set fecha_creacion='2023-04-28' where id = 3;
/*selects*/

select * from administrador where username='admin1' and contrasenia = '7c6a180b36896a0a8c02787eeafb0e4c';

/*para las especialidades de los medicos*/
insert into especialidades values(125,'especialidad3','descripcion de la especialidad3');
insert into especialidades_medicos values (125,124, 300, 'PENDIENTE');
update especialidades_medicos set estado = 'PENDIENTE', precio=200 WHERE id_especialidad=123 and id_medico=124;

/*para examenes laboratorio*/

select el.id_laboratorio, el.id_examen, e.nombre, el.precio, el.estado from examenes_laboratorios el join examenes e on (el.id_examen = e.id);

update examenes_laboratorios set estado = 'PENDIENTE' WHERE id_laboratorio = 123 and id_examen=123;

/*para especialidades medicas*/
select em.id_especialidad,em.id_medico, e.nombre, m.nombre, em.precio, em.estado from especialidades_medicos em join especialidades e on(em.id_especialidad=e.id) join medicos m on(m.id= em.id_medico);

/*para cobro app*/

select * from cobro_app;

insert into cobro_app (fecha_modificacion, porcentaje) values('2023-04-21',0.41);

/*para los horarrios
*/
select * from horarios_medicos;
insert into horarios_medicos (id_medico,hora_inicial,hora_final) values();
update horarios_medicos set hora_inicial = '08:30', hora_final ='12:30' where id_horario=1;

delete from horarios_medicos where id_horario = 5;


/*para consultas*/

update consultas set estado ='PENDIENTE' WHERE 	id = 1;

/*PARA ASIGNAR EXAMENES*/

select * from examenes_consulta;
insert into examenes_consulta values(125,1);
select ec.id_examen, e.nombre, ec.id_consulta from examenes_consulta ec join examenes e on(e.id=ec.id_examen) and ec.id_consulta = 1;

/*historialMedico*/
select * from consultas where id_paciente = 123;
select * from solicitudes where id_paciente =123;
select es.id_examen,e.nombre, e.descripcion, es.precio from solicitudes s join examenes_solicitud es on(es.id_solicitud = s.id) join examenes e on(e.id=es.id_examen) where s.id_paciente=123;

select p.nombre, count(c.id_paciente), sum(c.precio) from consultas c join pacientes p on(p.id = c.id_paciente) limit 5;
/*reporte top 5 pacientes con mayor ingreso*/
select p.nombre, count(p.id), sum(c.precio) from consultas c join pacientes p on(p.id = c.id_paciente) where c.fecha_creacion >= '2023-02-10' and  c.fecha_creacion <= '2023-04-10'  and c.id_medico=124 group by(p.id) having count(*)>=1 order by(sum(c.precio)) desc limit 5 ;

/*top 5 especialidades con mayor ingresos*/
select e.nombre, count(e.id), sum(c.precio) from consultas c join especialidades e on(e.id=c.id_especialidad) where c.fecha_creacion >= '2023-02-10' and  c.fecha_creacion <= '2023-04-10' and c.id_medico=124 group by(e.id) having count(*)>=1 order by(sum(c.precio)) desc limit 5;
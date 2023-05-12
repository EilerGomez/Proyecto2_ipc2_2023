package com.forproject2.forproject2ipc2_2023.datos;

import com.ctc.wstx.shaded.msv_core.datatype.xsd.datetime.IDateTimeValueType;
import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.Controladores.ControladorCargaDatos;
import com.forproject2.forproject2ipc2_2023.modelo.Consultas.Consultas;
import com.forproject2.forproject2ipc2_2023.modelo.Especialidades.Especialidades;
import com.forproject2.forproject2ipc2_2023.modelo.Examenes.Examenes;
import com.forproject2.forproject2ipc2_2023.modelo.Horarios.Horarios;
import com.forproject2.forproject2ipc2_2023.modelo.Solicitudes.Solicitudes;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Administrador.Administrador;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Laboratorios.Laboratorios;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Medicos.Medicos;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Paciente.Pacientes;
import lombok.Getter;
import lombok.Setter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Getter
@Setter

public class CargaDatosDB {
    //private final Conexion conexion = new Conexion();

    public void cargarDatos() {

    }

    public void guardarAdmin(Administrador administrador, Conexion conexion) {
        String query = "INSERT INTO administrador VALUES(?, ?, ?,?, ?, ?, ?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1, administrador.getIdUsuario());
            stmt.setString(2, administrador.getNombre());
            stmt.setString(3, administrador.getUserName());
            stmt.setString(4, administrador.getPassword());
            stmt.setString(5, administrador.getEmail());
            stmt.setString(6, administrador.getFechaNacimiento());
            stmt.setDouble(7, administrador.getSaldo());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar administrador: " + e);
            ControladorCargaDatos.errores.add("NO SE HA PODIDO AGREGAR EL ADMINISTARDOR: " + administrador.getIdUsuario() + " VERIFIQUE QUE LOS ATRIBUTOS ESTEN BIEN");
        }
    }

    public void guardarEspecialidad(Especialidades especialidad, Conexion conexion) {
        String query = "INSERT INTO especialidades VALUES(?, ?, ?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1, especialidad.getId());
            stmt.setString(2, especialidad.getNombre());
            stmt.setString(3, especialidad.getDescripcion());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar especialidad: " + e);
            ControladorCargaDatos.errores.add("NO SE HA PODIDO AGREGAR LA ESPECIALIDAD: " + especialidad.getId() + " VERIFIQUE QUE LOS ATRIBUTOS ESTEN BIEN");
        }
    }

    public void guardarTiposExamenes(Examenes examen, Conexion conexion) {
        String query = "INSERT INTO examenes VALUES(?, ?, ?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1, examen.getId());
            stmt.setString(2, examen.getNombre());
            stmt.setString(3, examen.getDescripcion());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar examen: " + e);
            ControladorCargaDatos.errores.add("NO SE HA PODIDO AGREGAR EL EXAMEN: " + examen.getId() + " VERIFIQUE QUE LOS ATRIBUTOS ESTEN BIEN");

        }
    }

    public void guardarMedicos(Medicos medico, Conexion conexion) {
        String query = "INSERT INTO medicos VALUES(?, ?, ?,?,?,?,?,?,?,?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1, medico.getIdUsuario());
            stmt.setString(2, medico.getNombre());
            stmt.setString(3, medico.getUserName());
            stmt.setString(4, medico.getPassword());
            stmt.setString(5, medico.getDireccion());
            stmt.setString(6, medico.getCui());
            stmt.setString(7, medico.getTelefono());
            stmt.setString(8, medico.getEmail());
            stmt.setString(9, medico.getFechaNacimiento());
            stmt.setDouble(10, medico.getSaldo());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar medico: " + e);
            ControladorCargaDatos.errores.add("NO SE HA PODIDO AGREGAR EL MEDICO: " + medico.getIdUsuario() + " VERIFIQUE QUE LOS ATRIBUTOS ESTEN BIEN");

        }
    }

    public void guardarHorarios_Medicos(Horarios horarios, int idMedico, Conexion conexion) {
        String query = "INSERT INTO horarios_medicos (id_medico, hora_inicial, hora_final) VALUES(?,?, ?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1, idMedico);
            stmt.setString(2, horarios.getHoraEntrada());
            stmt.setString(3, horarios.getHoraSalida());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar horario_medico: " + e);
            ControladorCargaDatos.errores.add("NO SE HA PODIDO AGREGAR EL HORARRIO DEL MEDICO: " +idMedico + " VERIFIQUE QUE EL MEDICO CON ESTE ID EXISTA");

        }
    }

    public void guardarEspecialidades_Medicos(Especialidades especialidad, int idMedico, String estado, Conexion conexion) {
        String query = "INSERT INTO especialidades_medicos (id_especialidad, id_medico, precio, estado) VALUES(?,?, ?,?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1, especialidad.getId());
            stmt.setInt(2, idMedico);
            stmt.setDouble(3, especialidad.getPrecio());
            stmt.setString(4, estado);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar especialidad medica: " + e);
            ControladorCargaDatos.errores.add("NO SE HA PODIDO AGREGAR LA ESPECIALIDAD DEL MEDICO: " + idMedico+ " CON ID:" + especialidad.getId() + " VERIFIQUE QUE LA ESPECIALIDAD EXISTA Y QUE EL MEDICO EXISTA EN EL ARCHIVO DE ENTARDA" );

        }
    }

    public void guardarLaboratorios(Laboratorios laboratorio, Conexion conexion) {
        String query = "INSERT INTO laboratorios VALUES(?, ?, ?,?,?,?,?,?,?,?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1, laboratorio.getIdUsuario());
            stmt.setString(2, laboratorio.getNombre());
            stmt.setString(3, laboratorio.getUserName());
            stmt.setString(4, laboratorio.getPassword());
            stmt.setString(5, laboratorio.getDireccion());
            stmt.setString(6, laboratorio.getCui());
            stmt.setString(7, laboratorio.getTelefono());
            stmt.setString(8, laboratorio.getEmail());
            stmt.setString(9, laboratorio.getFechaNacimiento());
            stmt.setDouble(10, laboratorio.getSaldo());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar laboratorio: " + e);
            ControladorCargaDatos.errores.add("NO SE HA PODIDO AGREGAR EL LABORATORIO: " + laboratorio.getIdUsuario() + " VERIFIQUE QUE LOS ATRIBUTOS ESTEN BIEN");

        }
    }

    public void guardarExamenes_Laboratorio(Examenes examen, int idLab, String estado, Conexion conexion) {
        String query = "INSERT INTO examenes_laboratorios VALUES(?, ?, ?,?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1, idLab);
            stmt.setInt(2, examen.getId());
            stmt.setDouble(3, examen.getPrecio());
            stmt.setString(4, estado);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar examen para lab: " + e);
            ControladorCargaDatos.errores.add("NO SE HA PODIDO AGREGAR EL EXAMEN QUE PERTENECE AL LABORATORIO: " + idLab + " CON EL ID DE EXAMEN: " + examen.getId() + " VERIFIQUE QUE EL LABORATORIO Y EL EXAMEN EXISTA DENTRO DEL ARCHIVO DE ENTRADA");

        }
    }

    public void guardarPacientes(Pacientes paciente, Conexion conexion) {
        String query = "INSERT INTO pacientes VALUES(?, ?, ?,?,?,?,?,?,?,?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1, paciente.getIdUsuario());
            stmt.setString(2, paciente.getNombre());
            stmt.setString(3, paciente.getUserName());
            stmt.setString(4, paciente.getPassword());
            stmt.setString(5, paciente.getDireccion());
            stmt.setString(6, paciente.getCui());
            stmt.setString(7, paciente.getTelefono());
            stmt.setString(8, paciente.getEmail());
            stmt.setString(9, paciente.getFechaNacimiento());
            stmt.setDouble(10, paciente.getSaldo());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar paciente: " + e);
            ControladorCargaDatos.errores.add("NO SE HA PODIDO AGREGAR EL PACIENTE: " + paciente.getIdUsuario() + " VERIFIQUE QUE LOS ATRIBUTOS ESTEN BIEN");

        }
    }

    public void guardarConsulta(Consultas consulta, Conexion conexion) {
        String query = "INSERT INTO consultas VALUES(?,?, ?,?,?,?,?,?,?,?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1, consulta.getIdConsulta());
            stmt.setInt(2, consulta.getIdEspecialidad());
            stmt.setInt(3, consulta.getIdPaciente());
            stmt.setInt(4, consulta.getIdMedico());
            stmt.setDouble(5, consulta.getPrecio());
            stmt.setDouble(6, consulta.getPorcentajeCobroApp());
            stmt.setString(7, consulta.getFechaAgendada());
            stmt.setString(8, consulta.getFechaCreacion());
            stmt.setString(9, consulta.getEstado());
            stmt.setString(10, consulta.getInforme());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            ControladorCargaDatos.errores.add("NO SE HA PODIDO AGREGAR LA CONSULTA: " + consulta.getIdConsulta() + " VERIFIQUE QUE EXISTA EL PACIENTE CON ID: " + consulta.getIdPaciente() + ", EL MEDICO CON ID: " + consulta.getIdMedico() + ", LA ESPECIALIDAD CON ID: " + consulta.getIdEspecialidad());

        }
    }

    public void guardarExamenes_Consulta(Examenes examen, int idConsulta, Conexion conexion) {
        String query = "INSERT INTO examenes_consulta VALUES(?,?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1, examen.getId());
            stmt.setInt(2, idConsulta);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            ControladorCargaDatos.errores.add("NO SE HA PODIDO AGREGAR EL EXAMEN DE LA CONSULTA CON ID: " + idConsulta + " VERIFIQUE QUE LA CONSULTA CON DICHO ID EXISTA EN EL ARCHIVO DE ENTRADA");

        }
    }

    public void guardarSolicitud(Solicitudes solicitud, Conexion conexion) {
        String query = "INSERT INTO solicitudes VALUES(?,?,?,?,?,?,?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1,solicitud.getIdSolicitud());
            stmt.setInt(2, solicitud.getIdLaboratorio());
            stmt.setInt(3,solicitud.getIdPaciente());
            stmt.setDouble(4,solicitud.getPorcentajeCobroApp());
            stmt.setString(5, solicitud.getFechaSolicitada());
            stmt.setString(6,solicitud.getFechaFinalizada());
            stmt.setString(7,solicitud.getEstado());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar solicitud: " + e);
            ControladorCargaDatos.errores.add("NO SE HA PODIDO AGREGAR LA SOLICITUD: " + solicitud.getIdSolicitud() + " VERIFIQUE QUE EXISTA EL LABORATORIO CON ID: " + solicitud.getIdLaboratorio() + ", Y EL PACIENTE CON ID: " + solicitud.getIdPaciente());

        }
    }

    public void guardarExamenes_Solicitud(Examenes examen, Conexion conexion, int idSolicitud){
        String query = "INSERT INTO examenes_solicitud VALUES(?,?,?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1, idSolicitud);
            stmt.setInt(2, examen.getId());
            stmt.setDouble(3,examen.getPrecio());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar examen_Solicitud: " + e);
            ControladorCargaDatos.errores.add("NO SE HA PODIDO AGREGAR EL EXAMEN DE LA SOLICITUD CON ID: " +idSolicitud + " VERIFIQUE QUE EXISTA DICHA SOLICITUD");

        }
    }


}


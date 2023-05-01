package com.forproject2.forproject2ipc2_2023.servicios;

import com.forproject2.forproject2ipc2_2023.datos.CargaDatosDB;
import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.modelo.Consultas.Consultas;
import com.forproject2.forproject2ipc2_2023.modelo.Especialidades.Especialidades;
import com.forproject2.forproject2ipc2_2023.modelo.Examenes.Examenes;
import com.forproject2.forproject2ipc2_2023.modelo.Horarios.Horarios;
import com.forproject2.forproject2ipc2_2023.modelo.Solicitudes.Solicitudes;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Administrador.Administrador;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Laboratorios.Laboratorios;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Medicos.Medicos;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Paciente.Pacientes;

import java.util.List;

public class ServicioCargaDatos {
    private final CargaDatosDB cargaDatosDb;

    public ServicioCargaDatos() {
        cargaDatosDb = new CargaDatosDB();
    }

    public void guardarAdmin(Administrador administrador, Conexion conexion) {
        cargaDatosDb.guardarAdmin(administrador, conexion);
    }

    public void guardarEspecialidad(Especialidades especialidad, Conexion conexion) {
        cargaDatosDb.guardarEspecialidad(especialidad, conexion);
    }

    public void guardarTiposExamenes(Examenes examen, Conexion conexion) {
        cargaDatosDb.guardarTiposExamenes(examen, conexion);
    }

    public void guardarMedicos(Medicos medico, Conexion conexion, List<Horarios> horarios, List<Especialidades> especialidades) {
        cargaDatosDb.guardarMedicos(medico, conexion);
        guardarHorarios_Medicos(horarios, medico.getIdUsuario(), conexion);
        guardarEspecialidades_Medicos(especialidades, medico.getIdUsuario(), "ACEPTADA", conexion);

    }

    public void guardarHorarios_Medicos(List<Horarios> horarios, int idMedico, Conexion conexion) {
        for (Horarios horario : horarios) {
            cargaDatosDb.guardarHorarios_Medicos(horario, idMedico, conexion);
        }

    }

    public void guardarEspecialidades_Medicos(List<Especialidades> especialidades, int medico, String estado, Conexion conexion) {
        for (Especialidades especialidad : especialidades) {
            cargaDatosDb.guardarEspecialidades_Medicos(especialidad, medico, estado, conexion);
        }
    }

    public void guardarLaboratorios(Laboratorios laboratorio, Conexion conexion, List<Examenes> examenes) {
        cargaDatosDb.guardarLaboratorios(laboratorio, conexion);
        guardarExamenes_Laboratorio(examenes, laboratorio.getIdUsuario(), "ACEPTADA", conexion);
    }

    public void guardarExamenes_Laboratorio(List<Examenes> examenes, int idLab, String estado, Conexion conexion) {
        for (Examenes examen : examenes) {
            cargaDatosDb.guardarExamenes_Laboratorio(examen, idLab, estado, conexion);
        }
    }

    public void guardarPacientes(Pacientes paciente, Conexion conexion) {
        cargaDatosDb.guardarPacientes(paciente,conexion);
    }

    public void guardarConsultas(Consultas consulta, Conexion conexion,List<Examenes> examenes){
        cargaDatosDb.guardarConsulta(consulta,conexion);
        guardarExamenes_Consulta(examenes,consulta.getIdConsulta(),conexion);
    }

    public void guardarExamenes_Consulta(List<Examenes> examenes, int idConsulta, Conexion conexion){
        for(Examenes examen: examenes){
            cargaDatosDb.guardarExamenes_Consulta(examen,idConsulta,conexion);
        }
    }

    public void guardarSolicitud(Solicitudes solicitud, Conexion conexion, List<Examenes> examenes){
        cargaDatosDb.guardarSolicitud(solicitud, conexion);
        guardarExamenes_Solicitud(examenes, solicitud.getIdSolicitud(),conexion);
    }

    public void guardarExamenes_Solicitud(List<Examenes> examenes, int idSolicitud, Conexion conexion){
        for(Examenes examen: examenes){
            cargaDatosDb.guardarExamenes_Solicitud(examen,conexion,idSolicitud);
        }
    }

}

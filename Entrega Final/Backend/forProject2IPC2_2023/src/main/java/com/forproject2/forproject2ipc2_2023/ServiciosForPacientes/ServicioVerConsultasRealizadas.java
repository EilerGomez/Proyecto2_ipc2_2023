package com.forproject2.forproject2ipc2_2023.ServiciosForPacientes;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.modelo.Consultas.Consultas;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioVerConsultasRealizadas {
    private ServicioHistorialMedico servicioHistorialMedico = new ServicioHistorialMedico();

    public List<Consultas> trerConsultasXTiempoIdEsp(Conexion conexion,int idPaciente, String desde, String hasta, int idEspecialidad) throws SQLException {
        return servicioHistorialMedico.traerConsultasDePaciente(conexion,idPaciente,desde,hasta).stream().filter(s-> s.getIdEspecialidad()==idEspecialidad).collect(Collectors.toList());
    }
}

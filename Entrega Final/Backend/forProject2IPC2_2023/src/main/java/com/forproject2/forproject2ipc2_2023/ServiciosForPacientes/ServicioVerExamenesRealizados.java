package com.forproject2.forproject2ipc2_2023.ServiciosForPacientes;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.modelo.Examenes.Examenes;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioVerExamenesRealizados {
    private ServicioHistorialMedico servicioHistorialMedico = new ServicioHistorialMedico();

    public List<Examenes> traerExamenesPrFechaYTipo(Conexion conexion, int idPaciente, String desde, String hasta, String tipo) throws SQLException {
        return servicioHistorialMedico.traerExamenesDePaciente(conexion,idPaciente,desde,hasta).stream().filter(s-> s.getNombre().equals(tipo)).collect(Collectors.toList());
    }
}

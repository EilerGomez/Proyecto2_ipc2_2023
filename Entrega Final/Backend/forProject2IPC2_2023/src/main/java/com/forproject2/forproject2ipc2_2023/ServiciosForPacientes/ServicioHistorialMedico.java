package com.forproject2.forproject2ipc2_2023.ServiciosForPacientes;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.DatosForPaciente.DatosHistorialMedico;
import com.forproject2.forproject2ipc2_2023.modelo.Consultas.Consultas;
import com.forproject2.forproject2ipc2_2023.modelo.Examenes.Examenes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioHistorialMedico {
    private DatosHistorialMedico datosHistorialMedico = new DatosHistorialMedico();

    public List<Consultas> traerConsultasDePaciente(Conexion conexion, int idPaciente, String desde, String hasta) throws SQLException {
        System.out.println("va aqui");
        return  convertirAConsultas(conexion,idPaciente,desde,hasta);
    }

    public List<Examenes> traerExamenesDePaciente(Conexion conexion, int idPaciente, String desde, String hasta) throws SQLException {
        return examenesPacientes(conexion,idPaciente,desde,hasta);
    }


    private List<Consultas> convertirAConsultas(Conexion conexion, int idPaciente, String desde, String hasta) throws SQLException {
        datosHistorialMedico.traerConsultas(conexion,desde,hasta,idPaciente);
        List<Consultas> listaConsultas = new ArrayList<>();
        while(conexion.getResultSet().next()){
            var newCon = new Consultas(conexion.getResultSet().getInt(1),conexion.getResultSet().getInt(3),conexion.getResultSet().getInt(4),
                    conexion.getResultSet().getInt(2),conexion.getResultSet().getDouble(6),conexion.getResultSet().getString(8),
                    conexion.getResultSet().getString(7),conexion.getResultSet().getDouble(5),conexion.getResultSet().getString(10),
                    conexion.getResultSet().getString(9));
            listaConsultas.add(newCon);
            System.out.println(newCon.getIdMedico() + ", " + newCon.getEstado());

        }
        return listaConsultas;
    }

    private List<Examenes> examenesPacientes(Conexion conexion, int idPaciente, String desde, String hasta) throws SQLException {
        List<Examenes> listaExamen = new ArrayList<>();
        datosHistorialMedico.traerExamenes(conexion,desde,hasta,idPaciente);
        while(conexion.getResultSet().next()){
            Examenes examen = new Examenes(conexion.getResultSet().getInt(1),conexion.getResultSet().getString(2),
                    conexion.getResultSet().getString(3),conexion.getResultSet().getDouble(4));
            listaExamen.add(examen);
        }
        return listaExamen;
    }
}

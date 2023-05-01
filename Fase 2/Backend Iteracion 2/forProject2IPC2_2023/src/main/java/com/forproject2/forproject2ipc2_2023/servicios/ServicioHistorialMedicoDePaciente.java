package com.forproject2.forproject2ipc2_2023.servicios;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.datos.DatosHistorialMedicoDePaciente;
import com.forproject2.forproject2ipc2_2023.modelo.Consultas.Consultas;
import com.forproject2.forproject2ipc2_2023.modelo.Examenes.Examenes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioHistorialMedicoDePaciente {
    private DatosHistorialMedicoDePaciente datosHistorialMedicoDePaciente = new DatosHistorialMedicoDePaciente();

    public List<Examenes> traerTodosLosExamenesDePacientes(Conexion conexion, int idPaciente) throws SQLException {
        return examenesPacientes(conexion,idPaciente);
    }

    public List<Consultas> tarerTodasLasConsultasHechasPaciente(Conexion conexion, int idPaciente, int idMedico) throws SQLException {
        return convertirAConsultas(conexion,idPaciente, idMedico);
    }

    private List<Examenes> examenesPacientes(Conexion conexion, int idPaciente) throws SQLException {
        List<Examenes> listaExamen = new ArrayList<>();
        datosHistorialMedicoDePaciente.traerTodosLosExamenesDePacientes(conexion, idPaciente);
        while(conexion.getResultSet().next()){
            Examenes examen = new Examenes(conexion.getResultSet().getInt(1),conexion.getResultSet().getString(2),
                    conexion.getResultSet().getString(3),conexion.getResultSet().getDouble(4));
            listaExamen.add(examen);
        }
        return listaExamen;
    }

    private List<Consultas> convertirAConsultas(Conexion conexion, int idPaciente, int idMedico) throws SQLException {
        datosHistorialMedicoDePaciente.tarerTodasLasConsultasHechasPaciente(conexion,idPaciente, idMedico);
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

}

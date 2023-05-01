package com.forproject2.forproject2ipc2_2023.datos;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosHistorialMedicoDePaciente {
    public void traerTodosLosExamenesDePacientes(Conexion conexion, int idPaciente){
        String query = "select es.id_examen,e.nombre, e.descripcion,es.precio from solicitudes s join examenes_solicitud es on(es.id_solicitud = s.id) join examenes e on(e.id=es.id_examen) where s.id_paciente=?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1,idPaciente);
            conexion.setResultSet(stmt.executeQuery());
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al consultar los examenes de pacientes:" + e);
        }
    }
    public void tarerTodasLasConsultasHechasPaciente(Conexion conexion, int idPaciente, int idMedico){
        String query = "select * from consultas where id_paciente = ? and id_medico =?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1,idPaciente);
            stmt.setInt(2, idMedico);
            conexion.setResultSet(stmt.executeQuery());
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al consultar las consultas de pacientes:" + e);
        }
    }
}

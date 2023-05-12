package com.forproject2.forproject2ipc2_2023.DatosForPaciente;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosHistorialMedico {
    public void traerConsultas(Conexion conexion, String desde, String hasta, int idPaciente){
        String query = "select * from consultas where id_paciente = ? and fecha_creacion >= ? and fecha_creacion<=?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1,idPaciente);
            stmt.setString(2,desde);
            stmt.setString(3, hasta);
            conexion.setResultSet(stmt.executeQuery());
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al consultar las consultas de historial medico:" + e);
        }
    }
    public void traerExamenes(Conexion conexion,  String desde, String hasta, int idPaciente){
        String query = "select es.id_examen,e.nombre, e.descripcion, es.precio from solicitudes s join examenes_solicitud es on(es.id_solicitud = s.id) join examenes e on(e.id=es.id_examen) where s.id_paciente=? and s.fecha_solicitada>=?\n" +
                "and fecha_solicitada<=?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1,idPaciente);
            stmt.setString(2,desde);
            stmt.setString(3, hasta);
            conexion.setResultSet(stmt.executeQuery());
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al consultar los examenes de historial medico:" + e);
        }
    }

}

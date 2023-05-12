package com.forproject2.forproject2ipc2_2023.DatosForPaciente;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.modelo.ExamenesSolicitud.ExamenesSolicitud;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosExamenesSolicitudes {

    public void guardarNuevoExamen(Conexion conexion, ExamenesSolicitud examenesSolicitud){
        String query = "insert into examenes_solicitud values(?,?,?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1,examenesSolicitud.getIdSolicitud());
            stmt.setInt(2,examenesSolicitud.getIdExamen());
            stmt.setDouble(3, examenesSolicitud.getPrecio());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar cosnulta: " + e);
        }
    }

    public void traerTodosExamenesSolicitud(Conexion conexion){
        String query = "select es.id_solicitud, es.id_examen, es.precio, e.nombre from examenes_solicitud es join examenes e on(es.id_examen = e.id);";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            conexion.setResultSet(stmt.executeQuery());
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al consultar los examens solicitudes:" + e);
        }
    }

    public void actualizarSaldoLab(Conexion conexion, Double ganado, int idLab){
        String query = "update laboratorios set saldo = saldo+? where id =?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setDouble(1,ganado);
            stmt.setInt(2,idLab);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error al actualizar saldo lab: " + e);
        }
    }


}

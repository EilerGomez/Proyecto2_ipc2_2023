package com.forproject2.forproject2ipc2_2023.datos;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.modelo.ExamenesLaboratorios.ExamenesLaboratorios;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosExamenesLaboratorio {
    public void traerExamenesLaboratorio(Conexion conexion) {
        String query = "select el.id_laboratorio, el.id_examen, e.nombre, el.precio, el.estado from examenes_laboratorios el join examenes e on (el.id_examen = e.id);";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            conexion.setResultSet(stmt.executeQuery());
        } catch (SQLException e) {
            System.out.println("Error al consultar los examenes laboratorio:" + e);
        }
    }

    public void actualizarEstadoExamenLaboratorio(Conexion conexion, ExamenesLaboratorios examen) {
        String query = "update examenes_laboratorios set estado =? WHERE id_laboratorio = ? and id_examen=?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setString(1,examen.getEstado());
            stmt.setInt(2, examen.getIdLaboratorio());
            stmt.setInt(3,examen.getIdExamen());
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error al actualizar examen laboratorio: " +e);
        }
    }

}

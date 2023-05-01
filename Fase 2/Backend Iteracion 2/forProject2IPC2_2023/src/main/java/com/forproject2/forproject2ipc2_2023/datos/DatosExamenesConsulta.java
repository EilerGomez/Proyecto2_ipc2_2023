package com.forproject2.forproject2ipc2_2023.datos;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosExamenesConsulta {
    public void traerExamenesDeConsulta(Conexion conexion, int idConsulta){
        String query = "select ec.id_examen, e.nombre, ec.id_consulta from examenes_consulta ec join examenes e on(e.id=ec.id_examen) and ec.id_consulta = ?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1,idConsulta);
            conexion.setResultSet(stmt.executeQuery());
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al consultar los examenes de la consulta:" + e);
        }
    }

    public void agregarExamenConsulta(Conexion conexion, int idConsulta, int idExamen){
        String query = "insert into examenes_consulta values(?,?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1, idExamen);
            stmt.setInt(2,idConsulta);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar examenes consulta: " + e);
        }
    }
}

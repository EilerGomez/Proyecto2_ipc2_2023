package com.forproject2.forproject2ipc2_2023.datos;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.modelo.Consultas.Consultas;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosConsultas {
    public void traerTodasConsultas(Conexion conexion) {
        String query = "select * from consultas;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            conexion.setResultSet(stmt.executeQuery());
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al consultar las consultas:" + e);
        }
    }

    public void actualizarConsulta(Conexion conexion, Consultas consulta){
        String query = "update consultas set estado =?, informe=? WHERE id = ?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setString(1,consulta.getEstado());
            stmt.setString(2,consulta.getInforme());
            stmt.setInt(3, consulta.getIdConsulta());
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error al actualizar consulta: " + e);
        }
    }

}

package com.forproject2.forproject2ipc2_2023.datos;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosEspecialidades {
    public void traerTodasEspecialidades(Conexion conexion) {
        String query = "select * from especialidades;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            conexion.setResultSet(stmt.executeQuery());
        } catch (SQLException e) {
            System.out.println("Error al consultar las especialidades:" + e);
        }
    }
}

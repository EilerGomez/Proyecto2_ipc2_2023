package com.forproject2.forproject2ipc2_2023.datos;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosLogin {



    public void traerUsuarios(String usuario, Conexion conexion) {

        String query = "select * from "+usuario+";";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);

            conexion.setResultSet(stmt.executeQuery());

        } catch (SQLException e) {
            System.out.println("Error al consultar " + usuario + ": " + e);
        }
    }

}

package com.forproject2.forproject2ipc2_2023.DatosForLaboratorio;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosLaboratorio {
    public void traerLaboratorio(Conexion conexion){
        String query = "select * from laboratorios;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            conexion.setResultSet(stmt.executeQuery());
        } catch (SQLException e) {
            System.out.println("Error al consultar el laboratorio:" + e);
        }
    }

}

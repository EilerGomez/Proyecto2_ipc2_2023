package com.forproject2.forproject2ipc2_2023.datos;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.modelo.cobroApp.CobroApicacion;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosCobroApp {

    public void crearCobroApp(Conexion conexion, CobroApicacion cobroApicacion) {
        String query = "insert into cobro_app (fecha_modificacion, porcentaje) values(?,?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setString(1,cobroApicacion.getFechaModificacion());
            stmt.setDouble(2,cobroApicacion.getPorcentaje());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar cobro de aplicacion: " + e);
        }
    }

    public void traerTodoElHistorial(Conexion conexion) {
        String query = "select * from cobro_app;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            conexion.setResultSet(stmt.executeQuery());
        } catch (SQLException e) {
            System.out.println("Error al consultar cobro de aplicacion:" + e);
        }
    }



}

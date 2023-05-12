package com.forproject2.forproject2ipc2_2023.DatosForLaboratorio;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.modelo.ResultadosSolicitud.ResultadosSolicitud;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosResultadosSolicitud {

    public void insertarNuevoResultado(Conexion conexion, ResultadosSolicitud resultado){
        String query = "insert into resultados_solicitudes values(?,?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1,resultado.getIdSolicitud());
            stmt.setBlob(2,resultado.getDatosArchivo());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar solicitud: " + e);
        }
    }

    public void traerArchivos(Conexion conexion, int idSolicitud){
        String query = "select * from resultados_solicitudes where id_solicitud=?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1, idSolicitud);
            conexion.setResultSet(stmt.executeQuery());
        } catch (SQLException e) {
            System.out.println("Error al consultar el laboratorio:" + e);
        }
    }


}

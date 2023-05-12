package com.forproject2.forproject2ipc2_2023.DatosForLaboratorio;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.modelo.ResultadosConsultas.ResultadosConsulta;
import com.forproject2.forproject2ipc2_2023.modelo.ResultadosSolicitud.ResultadosSolicitud;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosResultadosConsulta {

    public void insertarNuevoResultado(Conexion conexion, ResultadosConsulta resultado){
        String query = "insert into resultados_consultas values(?,?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1,resultado.getIdConsulta());
            stmt.setBlob(2,resultado.getDatosArchivo());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar resultado consulta: " + e);
        }
    }

    public void traerArchivos(Conexion conexion, int idConsulta){
        String query = "select * from resultados_consultas where id_consulta=?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1, idConsulta);
            conexion.setResultSet(stmt.executeQuery());
        } catch (SQLException e) {
            System.out.println("Error al consultar el resultado consulta:" + e);
        }
    }
}

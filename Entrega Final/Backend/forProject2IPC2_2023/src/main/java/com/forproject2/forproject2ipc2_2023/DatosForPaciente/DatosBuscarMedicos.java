package com.forproject2.forproject2ipc2_2023.DatosForPaciente;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosBuscarMedicos {

    public void traerMeeicos_Espedialidades(Conexion conexion, int idEsp) {
        String query = "select * from medicos m join especialidades_medicos em on(m.id=em.id_medico) where em.id_especialidad = ? and em.estado='ACEPTADA';";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1, idEsp);
            conexion.setResultSet(stmt.executeQuery());
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al consultar las especialidades_medicas:" + e);
        }
    }

    public void traerTodosMedicos(Conexion conexion) {
        String query = "select * from medicos;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            conexion.setResultSet(stmt.executeQuery());
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al consultar los medicos:" + e);
        }
    }


}

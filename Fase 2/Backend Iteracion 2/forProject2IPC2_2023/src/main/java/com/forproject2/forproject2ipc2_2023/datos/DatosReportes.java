package com.forproject2.forproject2ipc2_2023.datos;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosReportes {
    public void traerPacientes(Conexion conexion, String desde, String hasta, int idMedico) {

        String query = "select p.nombre, count(p.id), sum(c.precio) from consultas c join pacientes p on(p.id = c.id_paciente) where c.fecha_creacion >= ? and  c.fecha_creacion <= ? and c.id_medico=?  group by(p.id) having count(*)>=1 order by(sum(c.precio)) desc limit 5 ;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setString(1,desde);
            stmt.setString(2,hasta);
            stmt.setInt(3, idMedico);
            conexion.setResultSet(stmt.executeQuery());

        } catch (SQLException e) {
            System.out.println("Error al consultas top 5 pacientes con mayor ingreso" + e);
        }
    }
    public void traerEspecialidades(Conexion conexion, String desde, String hasta, int idMedico) {

        String query = "select e.nombre, count(e.id), sum(c.precio) from consultas c join especialidades e on(e.id=c.id_especialidad) where c.fecha_creacion >= ? and  c.fecha_creacion <= ? and c.id_medico=? group by(e.id) having count(*)>=1 order by(sum(c.precio)) desc limit 5;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setString(1,desde);
            stmt.setString(2,hasta);
            stmt.setInt(3,idMedico);
            conexion.setResultSet(stmt.executeQuery());

        } catch (SQLException e) {
            System.out.println("Error al consultas top 5 especialidades con mayor ingreso" + e);
        }
    }

}

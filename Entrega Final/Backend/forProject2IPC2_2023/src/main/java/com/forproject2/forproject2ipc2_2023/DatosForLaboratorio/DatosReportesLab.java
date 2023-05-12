package com.forproject2.forproject2ipc2_2023.DatosForLaboratorio;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosReportesLab {

    public void traerTop5Pacientes(Conexion conexion, String desde, String hasta, int idlab){
        String query = "select p.nombre, count(p.id), sum(es.precio) from solicitudes s join examenes_solicitud es on(s.id=es.id_solicitud) join pacientes p on(p.id=s.id_paciente) where s.fecha_solicitada >= ? and  s.fecha_solicitada <= ?  and s.id_laboratorio=? group by(p.id) having count(*)>=1 order by(sum(es.precio)) desc limit 5 ;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setString(1, desde);
            stmt.setString(2,hasta);
            stmt.setInt(3, idlab);
            conexion.setResultSet(stmt.executeQuery());
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al consultar top 5 pacientes con mayor ingreso:" + e);
        }
    }

    public void  traerTop5Examenes(Conexion conexion, String desde, String hasta, int idlab){
        String query = "select e.nombre, count(e.id), sum(es.precio) from solicitudes s join examenes_solicitud es on(s.id=es.id_solicitud) join examenes e on(e.id=es.id_examen) where s.fecha_solicitada >= ? and  s.fecha_solicitada <= ?  and s.id_laboratorio=? group by(e.id) having count(*)>=1 order by(sum(es.precio)) desc limit 5 ;\n";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setString(1, desde);
            stmt.setString(2,hasta);
            stmt.setInt(3, idlab);
            conexion.setResultSet(stmt.executeQuery());
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al consultar top 5 pacientes con mayor ingreso:" + e);
        }
    }

}

package com.forproject2.forproject2ipc2_2023.DatosForPaciente;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.modelo.Solicitudes.Solicitudes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosSolicitudes {
    public void guardarNuevaSolicitud(Conexion conexion, Solicitudes solicitud){
        String query = "insert into solicitudes (id_laboratorio, id_paciente, porcentaje_app, fecha_solicitada, estado) values (?,?,?,?,?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1,solicitud.getIdLaboratorio());
            stmt.setInt(2,solicitud.getIdPaciente());
            stmt.setDouble(3,solicitud.getPorcentajeCobroApp());
            stmt.setString(4,solicitud.getFechaSolicitada());
            stmt.setString(5,solicitud.getEstado());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar solicitud: " + e);
        }
    }

    public void traerTodasSolicitudes(Conexion conexion){
        String query = "select * from solicitudes;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            conexion.setResultSet(stmt.executeQuery());
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al consultar las solicitudes:" + e);
        }
    }

    public void eliminarSolicitud(Conexion conexion,int idSolicitud){
        String query = "delete from solicitudes where id =?";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1,idSolicitud);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar solicitud: " + e);
        }
    }

    public void actualizarSolicitud(Conexion conexion, Solicitudes solicitud){
        String query = "update solicitudes set fecha_finalizada = ?, estado = ? WHERE id =?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setString(1,solicitud.getFechaFinalizada());
            stmt.setString(2,solicitud.getEstado());
            stmt.setInt(3,solicitud.getIdSolicitud());
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error al actualizar consulta: " + e);
        }
    }


    public void traerUltimaSolicitud(Conexion conexion, int idPaciente){
        String query = "SELECT  max(id) as maximo from solicitudes where id_paciente=?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1,idPaciente);
            conexion.setResultSet(stmt.executeQuery());
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al consultar las solicitudes:" + e);
        }
    }





}

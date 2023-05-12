package com.forproject2.forproject2ipc2_2023.datos;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.modelo.Especialidades_medicos.Especialidades_Medicos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosEspecialidadesMedicos {


    public void traerTodasEspecialidades(Conexion conexion) {
        String query = "select em.id_especialidad,em.id_medico, e.nombre, m.nombre, em.precio, em.estado from especialidades_medicos em join especialidades e on(em.id_especialidad=e.id) join medicos m on(m.id= em.id_medico);";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            conexion.setResultSet(stmt.executeQuery());
        } catch (SQLException e) {
            System.out.println("Error al consultar las especialidades:" + e);
        }
    }

    public void actualizarEspecialidadesMedicas(Conexion conexion, Especialidades_Medicos especialidad) {
        String query = "update especialidades_medicos set estado = ?,precio=? WHERE id_especialidad=? and id_medico=?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setString(1, especialidad.getEstado());
            stmt.setDouble(2,especialidad.getPrecio());
            stmt.setInt(3, especialidad.getIdEspecialidad());
            stmt.setInt(4, especialidad.getIdMedico());
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error al actualizar especialidadesmedicas: " + e);
        }
    }

    public void agregarNuevaEspecialidadAMedico(Conexion conexion, Especialidades_Medicos especialidad) {
        String query = "insert into especialidades_medicos values (?,?, ?, ?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1, especialidad.getIdEspecialidad());
            stmt.setInt(2,especialidad.getIdMedico());
            stmt.setDouble(3,especialidad.getPrecio());
            stmt.setString(4, especialidad.getEstado());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar cobro de aplicacion: " + e);
        }
    }


    /* public static void actualizarArchivosEntrada(){
        String query = "update carga_archivos set cargado =1 where numero =1;";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
        }
    }*/
}

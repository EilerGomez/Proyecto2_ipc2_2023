package com.forproject2.forproject2ipc2_2023.DatosForPaciente;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.modelo.HistorialRecargas.HistorialRecargas;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosRecargarSaldoPaciente {
    //update pacientes set saldo = saldo +10 where id=123;
    //insert into recargas (id_paciente, recarga,fecha_realizada) values(123,10,'2023-5-2');

    public void actualizarSaldopaciente(Conexion conexion, int idPaciente, int recarga){
        String query = "update pacientes set saldo = saldo +? where id=?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1,recarga);
            stmt.setInt(2,idPaciente);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error al actualizar saldo lab: " + e);
        }
    }

    public void agregarhistorialRecargas(Conexion conexion, HistorialRecargas recarga){
        String query = "insert into recargas (id_paciente, recarga,fecha_realizada) values(?,?,?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1,recarga.getIdPaciente());
            stmt.setInt(2,recarga.getRecarga());
            stmt.setString(3,recarga.getFecha());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar cosnulta: " + e);
        }
    }

    public void traerHistorialMedico(Conexion conexion){
        String query = "select * from recargas;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            conexion.setResultSet(stmt.executeQuery());
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al consultar el historial de recargas:" + e);
        }
    }
}

package com.forproject2.forproject2ipc2_2023.datos;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.modelo.Consultas.Consultas;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosConsultas {
    public void traerTodasConsultas(Conexion conexion) {
        String query = "select * from consultas;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            conexion.setResultSet(stmt.executeQuery());
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al consultar las consultas:" + e);
        }
    }

    public void actualizarConsulta(Conexion conexion, Consultas consulta){
        String query = "update consultas set estado =?, informe=? WHERE id = ?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setString(1,consulta.getEstado());
            stmt.setString(2,consulta.getInforme());
            stmt.setInt(3, consulta.getIdConsulta());
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error al actualizar consulta: " + e);
        }
    }

    public void guardarNuevaConsulta(Conexion conexion, Consultas consulta){
        String query = "insert into consultas (id_especialidad, id_paciente, id_medico, precio, porcentaje_app, fecha_agendada, fecha_creacion, estado, informe) values (?,?,?,?,?,?,?,?,?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1,consulta.getIdEspecialidad());
            stmt.setInt(2,consulta.getIdPaciente());
            stmt.setInt(3,consulta.getIdMedico());
            stmt.setDouble(4,consulta.getPrecio());
            stmt.setDouble(5,consulta.getPorcentajeCobroApp());
            stmt.setString(6,consulta.getFechaAgendada());
            stmt.setString(7, consulta.getFechaCreacion());
            stmt.setString(8,consulta.getEstado());
            stmt.setString(9,consulta.getInforme());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar cosnulta: " + e);
        }
    }

    public void actualizarSaldoAdmin(Conexion conexion, Double ganado){
        String query = "update administrador set saldo = saldo+?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setDouble(1,ganado);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error al actualizar consulta: " + e);
        }
    }
    public void actualizarSaldoMedico(Conexion conexion, Double ganado, int idMedico){
        String query = "update medicos set saldo = saldo +? where id = ?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setDouble(1,ganado);
            stmt.setInt(2, idMedico);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error al actualizar saldo medico: " + e);
        }
    }

    public double traerSaldoPaciente(Conexion conexion, int idPaciente){
        double saldo=0;
        String query = "select * from pacientes WHERE id=?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setInt(1,idPaciente);
            conexion.setResultSet(stmt.executeQuery());
            System.out.println("hecho");
            while(conexion.getResultSet().next()){
                saldo=conexion.getResultSet().getDouble(10);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar saldo medico:" + e);
        }
        return saldo;
    }

    public void actualizarSaldoPaciente(Conexion conexion, double saldo, int idpaciente){
        String query = "update pacientes set saldo = saldo -? where id = ?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setDouble(1,saldo);
            stmt.setInt(2, idpaciente);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error al actualizar saldo medico: " + e);
        }
    }



}

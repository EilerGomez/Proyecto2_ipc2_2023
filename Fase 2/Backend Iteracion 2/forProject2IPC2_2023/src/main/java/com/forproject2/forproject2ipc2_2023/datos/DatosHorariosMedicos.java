package com.forproject2.forproject2ipc2_2023.datos;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.modelo.Horarios.Horarios;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosHorariosMedicos {
    public void guardarHorarios_Medicos(Horarios horarios, Conexion conexion) {
        String query = "INSERT INTO horarios_medicos (id_medico, hora_inicial, hora_final) VALUES(?,?, ?);";
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
            stmt.setInt(1, horarios.getIdMedico());
            stmt.setString(2, horarios.getHoraEntrada());
            stmt.setString(3, horarios.getHoraSalida());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("hecho");
        } catch (SQLException e) {
            System.out.println("Error al guardar horario_medico: " + e);
        }
    }

    public void traerTodosLosHorarrios(Conexion conexion) {
        String query = "select * from horarios_medicos;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            conexion.setResultSet(stmt.executeQuery());
        } catch (SQLException e) {
            System.out.println("Error al consultar los horarrios medicos:" + e);
        }
    }
    public void editarHorarrios(Conexion conexion, Horarios horarrio) {
        String query = "update horarios_medicos set hora_inicial = ?, hora_final =? where id_horario=?;";
        try {
            PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
            stmt.setString(1,horaEntrada(horarrio));
            stmt.setString(2, horaSalida(horarrio));
            stmt.setInt(3, horarrio.getIdHorario());
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error al actualizar horario: " + e);
        }
    }

    private String horaEntrada(Horarios horario){
        String [] splits = horario.getHoraEntrada().split(":");
        String hora="";
        if(splits.length==2){
            hora= horario.getHoraEntrada()+":00";
        }else{
            hora= horario.getHoraEntrada();
        }
        System.out.println(hora);
        return hora;
    }
    private String horaSalida(Horarios horario){
        String [] splits = horario.getHoraSalida().split(":");
        String hora ="";
        if(splits.length==2){
            hora= horario.getHoraSalida()+":00";
        }else{
            hora= horario.getHoraSalida();
        }
        System.out.println(hora);
        return hora;
    }


}

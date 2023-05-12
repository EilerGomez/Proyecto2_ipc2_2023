package com.forproject2.forproject2ipc2_2023.servicios;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.datos.DatosLogin;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Administrador.Administrador;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Personas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicioLogin {
    private final DatosLogin datosLogin;
    public ServicioLogin() {
        datosLogin = new DatosLogin();
    }

    public Administrador leerAdmins(String username, Conexion conexion, String password) throws SQLException {

        return  validar(conexion,username,password).orElse(null);
    }


    public Optional<Administrador> validar(Conexion conexion, String username, String password) throws SQLException {
        List<Administrador> listaAdministrador = new ArrayList<>();
        datosLogin.traerUsuarios("administrador", conexion);
        while (conexion.getResultSet().next()){
            var admin = new Administrador(conexion.getResultSet().getInt(1),conexion.getResultSet().getString(2),conexion.getResultSet().getString(3),
                    conexion.getResultSet().getString(4),conexion.getResultSet().getString(5),conexion.getResultSet().getString(6), conexion.getResultSet().getDouble(7));
            listaAdministrador.add(admin);
        }
        //return estudiantes.stream().filter(s -> s.getId() == id).findFirst();

        return  listaAdministrador.stream().filter(s -> s.getUserName().equals(username) && s.getPassword().equals(password)).findFirst();
    }
    //para los demas usuarios

    public Optional<Personas> validarDemasUsuarios(Conexion conexion, String username, String password, String area) throws SQLException {
        List<Personas> listaPersonas = new ArrayList<>();
        datosLogin.traerUsuarios(area,conexion);
        while(conexion.getResultSet().next()){
            var persona = new Personas(conexion.getResultSet().getInt(1),conexion.getResultSet().getString(2),conexion.getResultSet().getString(3),
                    conexion.getResultSet().getString(4),conexion.getResultSet().getString(8),conexion.getResultSet().getString(9),
                    conexion.getResultSet().getDouble(10),conexion.getResultSet().getString(5),conexion.getResultSet().getString(6),
                    conexion.getResultSet().getString(7));
            listaPersonas.add(persona);
        }
        return  listaPersonas.stream().filter(s -> s.getUserName().equals(username) && s.getPassword().equals(password)).findFirst();

    }

    public Personas leerDemasUsuarios(Conexion conexion, String username, String pasword, String area) throws SQLException {
        String tipoPersona="";
        switch (area){
            case "2": tipoPersona="medicos";
                break;
            case "3": tipoPersona="pacientes";
                break;
            case "4": tipoPersona="laboratorios";
                break;
            default:
                break;
        }
        return validarDemasUsuarios(conexion,username,pasword,tipoPersona).orElse(null);
    }

    public Optional<Personas> llamarPersona(Conexion conexion, String username, String area) throws SQLException { //lo use para el reporte de ver saldo del medico/paciente o laboratorio
        List<Personas> listaPersonas = new ArrayList<>();
        datosLogin.traerUsuarios(area,conexion);
        while(conexion.getResultSet().next()){
            var persona = new Personas(conexion.getResultSet().getInt(1),conexion.getResultSet().getString(2),conexion.getResultSet().getString(3),
                    conexion.getResultSet().getString(4),conexion.getResultSet().getString(8),conexion.getResultSet().getString(9),
                    conexion.getResultSet().getDouble(10),conexion.getResultSet().getString(5),conexion.getResultSet().getString(6),
                    conexion.getResultSet().getString(7));
            listaPersonas.add(persona);
        }
        return  listaPersonas.stream().filter(s -> s.getUserName().equals(username)).findFirst();

    }
    public Personas leerUsers(Conexion conexion, String username,  String area) throws SQLException {//lo use para reportes
        String tipoPersona="";
        switch (area){
            case "2": tipoPersona="medicos";
                break;
            case "3": tipoPersona="pacientes";
                break;
            case "4": tipoPersona="laboratorios";
                break;
            default:
                break;
        }
        return llamarPersona(conexion,username,tipoPersona).orElse(null);
    }


}

package com.forproject2.forproject2ipc2_2023.Controladores;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.modelo.Login.Login;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Administrador.Administrador;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Laboratorios.Laboratorios;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Medicos.Medicos;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Paciente.Pacientes;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Personas;
import com.forproject2.forproject2ipc2_2023.servicios.ServicioLogin;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.SQLException;

@WebServlet(name = "servletLogin", urlPatterns = {"/servletLogin/*"})
public class ControladorLogin extends HttpServlet {
    private final UtilidadesJSON<Administrador> jsonAdmin;
    private final UtilidadesJSON<Medicos> jsonMedico;
    private final UtilidadesJSON<Pacientes> jsonPaciente;
    private final UtilidadesJSON<Laboratorios> jsonLab;

    private final UtilidadesJSON<Login> jsonLogin;
    private final UtilidadesJSON<Personas> jsonPerson;
    private final ServicioLogin servicioLogin;
    public static Conexion conexion = new Conexion();

    public ControladorLogin() {
        jsonAdmin = new UtilidadesJSON<>();
        jsonMedico = new UtilidadesJSON<>();
        jsonPaciente = new UtilidadesJSON<>();
        jsonLab = new UtilidadesJSON<>();
        jsonLogin = new UtilidadesJSON<>();
        servicioLogin = new ServicioLogin();
        jsonPerson=new UtilidadesJSON<>();
    }



    private void verificarUsuario(HttpServletResponse response,String userName, String password, String area) throws IOException, SQLException {
        switch (area){
            case "1":
                if (servicioLogin.leerAdmins(userName, conexion, password) == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
                jsonAdmin.enviarComoJSON(response, servicioLogin.leerAdmins(userName, conexion, password));
                break;
            default:
                if(servicioLogin.leerDemasUsuarios(conexion,userName,password,area)==null){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
                jsonPerson.enviarComoJSON(response,servicioLogin.leerDemasUsuarios(conexion,userName,password,area));
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        conexion.conectar();
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            var usuario = jsonLogin.leerDeJSON(request, Login.class);
            try {
                verificarUsuario(response,usuario.getUserName(),getMD5(usuario.getPassword()),usuario.getArea());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getMD5(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] encBytes = md.digest(texto.getBytes());
            BigInteger numero = new BigInteger(1, encBytes);
            String encString = numero.toString(16);
            while (encString.length() < 32) {
                encString = "0" + encString;
            }
            return encString;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        conexion.cerrarConexion();
        jsonLogin.enviarComoJSON(resp,"Conexion cerrada");
    }
}

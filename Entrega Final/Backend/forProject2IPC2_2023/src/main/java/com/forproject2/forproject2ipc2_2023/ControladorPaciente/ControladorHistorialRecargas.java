package com.forproject2.forproject2ipc2_2023.ControladorPaciente;

import com.forproject2.forproject2ipc2_2023.Controladores.ControladorLogin;
import com.forproject2.forproject2ipc2_2023.ServiciosForPacientes.ServicioRecargarSaldopaciente;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;
import jdk.jshell.execution.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletRecargas", urlPatterns = "/ServletRecargas/*")
public class ControladorHistorialRecargas extends HttpServlet {
    private ServicioRecargarSaldopaciente servicioRecargarSaldopaciente= new ServicioRecargarSaldopaciente();
    private UtilidadesJSON jsonRec = new UtilidadesJSON();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String[] splits = pathInfo.split("/");
        //servlet/idMedico/recarga
        if(splits.length==3){
            int idPaciente = Integer.parseInt(splits[1]);
            int recarga = Integer.parseInt(splits[2]);
            servicioRecargarSaldopaciente.recargarSaldo(ControladorLogin.conexion,idPaciente,recarga);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String [] splits = pathInfo.split("/");
        if(splits.length==2){
            int idPaciente = Integer.parseInt(splits[1]);
            try {
                var lista = servicioRecargarSaldopaciente.historialrecargasPorId(ControladorLogin.conexion,idPaciente);
                if(lista.size()==0){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                jsonRec.enviarComoJSON(response,lista);
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

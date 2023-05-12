package com.forproject2.forproject2ipc2_2023.Controladores;

import com.forproject2.forproject2ipc2_2023.servicios.ServicioExamenesConsulta;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletExamenesConsulta", urlPatterns = {"/ServletExamenesConsulta/*"})
public class ControladorExamenesConsultas extends HttpServlet {
    private UtilidadesJSON jsonExCon = new UtilidadesJSON();
    private ServicioExamenesConsulta servicioExamenesConsulta = new ServicioExamenesConsulta();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] splits = pathInfo.split("/");
        if (splits.length == 2) {
            int idConsulta = Integer.parseInt(splits[1]);
            try {
                if (servicioExamenesConsulta.traerExamenesDeConsulta(ControladorLogin.conexion, idConsulta).size() == 0) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                jsonExCon.enviarComoJSON(response, servicioExamenesConsulta.traerExamenesDeConsulta(ControladorLogin.conexion, idConsulta));
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] splits = pathInfo.split("/");
        if(splits.length==3){
            //servlet/1/123
            //servlet/idConsulta/idExamen
            int idConsulta = Integer.parseInt(splits[1]);
            int idExamen = Integer.parseInt(splits[2]);
            servicioExamenesConsulta.guardarExamenesConsulta(ControladorLogin.conexion,idExamen,idConsulta);
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
    }
}

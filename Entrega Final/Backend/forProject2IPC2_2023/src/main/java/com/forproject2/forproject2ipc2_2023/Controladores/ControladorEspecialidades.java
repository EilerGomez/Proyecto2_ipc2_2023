package com.forproject2.forproject2ipc2_2023.Controladores;

import com.forproject2.forproject2ipc2_2023.servicios.ServicioEspecialidades;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletEspecialidades", urlPatterns = {"/ServletEspecialidades/*"})
public class ControladorEspecialidades extends HttpServlet {
    private UtilidadesJSON jsonEsp = new UtilidadesJSON<>();
    private ServicioEspecialidades servicioEspecialidades= new ServicioEspecialidades();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            try {
                response.setStatus(HttpServletResponse.SC_OK);
                jsonEsp.enviarComoJSON(response,servicioEspecialidades.traerTodasLasEspecialidades(ControladorLogin.conexion));
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);

    }
}

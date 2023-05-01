package com.forproject2.forproject2ipc2_2023.Controladores;

import com.forproject2.forproject2ipc2_2023.servicios.ServicioExamenes;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletExamenes", urlPatterns = {"/ServletExamenes/*"})
public class ControladorExamenes extends HttpServlet {

    private ServicioExamenes servicioExamenes;
    private UtilidadesJSON jsonExamenes;

    public ControladorExamenes(){
        servicioExamenes=new ServicioExamenes();
        jsonExamenes= new UtilidadesJSON();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            try {
                var lista = servicioExamenes.traerTodosExamenes(ControladorLogin.conexion);
                jsonExamenes.enviarComoJSON(response,lista);
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

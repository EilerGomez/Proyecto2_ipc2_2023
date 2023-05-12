package com.forproject2.forproject2ipc2_2023.ControladorPaciente;

import com.forproject2.forproject2ipc2_2023.Controladores.ControladorLogin;
import com.forproject2.forproject2ipc2_2023.ServiciosForPacientes.ServicioExamenesSolicitud;
import com.forproject2.forproject2ipc2_2023.modelo.ExamenesSolicitud.ExamenesSolicitud;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletExamenesSolicitud", urlPatterns = "/ServletExamenesSolicitud/*")

public class ControladorExamenesSolicitud extends HttpServlet {
    private UtilidadesJSON jsonExamenSoli= new UtilidadesJSON();
    private ServicioExamenesSolicitud servicioExamenesSolicitud= new ServicioExamenesSolicitud();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String [] splits = pathInfo.split("/");
        int idSolicitud = Integer.parseInt(splits[1]);
        try {
            var lista=servicioExamenesSolicitud.traerExamenesSolicitudPorId(ControladorLogin.conexion,idSolicitud);
            if(lista.size()==0){
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            jsonExamenSoli.enviarComoJSON(response,lista);
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
        }
        String [] splits = pathInfo.split("/");

        //servlet/porcentajeapp/idlab/idpaciente
        //servlet/0.04/15/123
        double porcentaje = Double.parseDouble(splits[1]);
        int idLab = Integer.parseInt(splits[2]);
        int idPac = Integer.parseInt(splits[3]);
        ExamenesSolicitud lista = (ExamenesSolicitud) jsonExamenSoli.leerDeJSON(request, ExamenesSolicitud.class);
        if(servicioExamenesSolicitud.guardarExamenSolicitud(ControladorLogin.conexion,lista,porcentaje,idLab,idPac)==false){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        jsonExamenSoli.enviarComoJSON(response,lista);
        response.setStatus(HttpServletResponse.SC_OK);
        return;

    }
}

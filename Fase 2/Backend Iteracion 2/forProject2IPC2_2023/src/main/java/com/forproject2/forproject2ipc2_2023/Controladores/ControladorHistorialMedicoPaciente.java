package com.forproject2.forproject2ipc2_2023.Controladores;

import com.forproject2.forproject2ipc2_2023.servicios.ServicioHistorialMedicoDePaciente;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletHistorialMedico", urlPatterns = {"/ServletHistorialMedico/*"})
public class ControladorHistorialMedicoPaciente extends HttpServlet {
    private ServicioHistorialMedicoDePaciente servicioHistorialMedicoDePaciente = new ServicioHistorialMedicoDePaciente();
    private UtilidadesJSON jsonHistMedic = new UtilidadesJSON();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] splits = pathInfo.split("/");
        //1 representara las consultas, 2 representara los examenes
        //servlet../1/123/124 --> 123 es el id del paciente -->124 es el idMedico
        if(splits.length==4){
            String cuestion = splits[1];
            int idPaicenye = Integer.parseInt(splits[2]);
            int idMedico = Integer.parseInt(splits[3]);
            if(cuestion.equals("1")){
                try {
                    if(servicioHistorialMedicoDePaciente.tarerTodasLasConsultasHechasPaciente(ControladorLogin.conexion,idPaicenye,idMedico).size()==0){
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                        return;
                    }
                    jsonHistMedic.enviarComoJSON(response,servicioHistorialMedicoDePaciente.tarerTodasLasConsultasHechasPaciente(ControladorLogin.conexion,idPaicenye,idMedico));
                    response.setStatus(HttpServletResponse.SC_OK);
                    return;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else if(cuestion.equals("2")){
                try {
                    if(servicioHistorialMedicoDePaciente.traerTodosLosExamenesDePacientes(ControladorLogin.conexion,idPaicenye).size()==0){
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                        return;
                    }
                    jsonHistMedic.enviarComoJSON(response,servicioHistorialMedicoDePaciente.traerTodosLosExamenesDePacientes(ControladorLogin.conexion,idPaicenye));
                    response.setStatus(HttpServletResponse.SC_OK);
                    return;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

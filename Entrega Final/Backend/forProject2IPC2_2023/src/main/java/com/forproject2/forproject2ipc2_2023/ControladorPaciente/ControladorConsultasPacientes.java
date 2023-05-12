package com.forproject2.forproject2ipc2_2023.ControladorPaciente;

import com.forproject2.forproject2ipc2_2023.Controladores.ControladorLogin;
import com.forproject2.forproject2ipc2_2023.ServiciosForPacientes.ServicioExamenesSolicitud;
import com.forproject2.forproject2ipc2_2023.servicios.ServicioConsultas;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletConsultasPacientes", urlPatterns = "/ServletConsultasPacientes/*")
public class ControladorConsultasPacientes extends HttpServlet {
    private ServicioConsultas servicioConsultas = new ServicioConsultas();
    private UtilidadesJSON jsonConsPAc = new UtilidadesJSON();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String [] splits = pathInfo.split("/");
        if(splits.length==3){
            //servlet/estado/paciente
            String estado = splits[1];
            int idPaciente = Integer.parseInt(splits[2]);
            try {
                var lista = servicioConsultas.consultasPorEstadoPacientes(ControladorLogin.conexion,estado,idPaciente);
                if(lista.size()==0){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                jsonConsPAc.enviarComoJSON(response,lista);
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

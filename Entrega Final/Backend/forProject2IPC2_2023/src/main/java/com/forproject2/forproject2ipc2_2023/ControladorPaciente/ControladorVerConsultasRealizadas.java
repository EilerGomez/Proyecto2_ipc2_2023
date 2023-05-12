package com.forproject2.forproject2ipc2_2023.ControladorPaciente;

import com.forproject2.forproject2ipc2_2023.Controladores.ControladorLogin;
import com.forproject2.forproject2ipc2_2023.ServiciosForPacientes.ServicioVerConsultasRealizadas;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletVerConsultasRealizadas", urlPatterns = "/ServletVerConsultasRealizadas/*")
public class ControladorVerConsultasRealizadas extends HttpServlet {
    private UtilidadesJSON jsonConsRea= new UtilidadesJSON();

    private ServicioVerConsultasRealizadas servicioVerConsultasRealizadas= new ServicioVerConsultasRealizadas();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String[] splits = pathInfo.split("/");
        //servlet/idpaciente/desde/hasta/idespecialidad
        if(splits.length==5){
            int idPaciente = Integer.parseInt(splits[1]);
            String desde = splits[2];
            String hasta = splits[3];
            int idEspecialidad= Integer.parseInt(splits[4]);
            try {
                var lista= servicioVerConsultasRealizadas.trerConsultasXTiempoIdEsp(ControladorLogin.conexion,idPaciente,desde,hasta,idEspecialidad);
                if(lista.size()==0){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                jsonConsRea.enviarComoJSON(response,lista);
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

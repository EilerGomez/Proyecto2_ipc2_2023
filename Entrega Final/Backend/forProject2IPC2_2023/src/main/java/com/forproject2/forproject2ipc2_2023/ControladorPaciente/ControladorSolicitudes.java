package com.forproject2.forproject2ipc2_2023.ControladorPaciente;

import com.forproject2.forproject2ipc2_2023.Controladores.ControladorLogin;
import com.forproject2.forproject2ipc2_2023.ServiciosForPacientes.ServicioSolicitudes;
import com.forproject2.forproject2ipc2_2023.modelo.Fecha.Fecha;
import com.forproject2.forproject2ipc2_2023.modelo.Solicitudes.Solicitudes;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletSolicitudes", urlPatterns = "/ServletSolicitudes/*")
public class ControladorSolicitudes extends HttpServlet {
    private UtilidadesJSON jsonSolicitudes = new UtilidadesJSON();
    private ServicioSolicitudes servicioSolicitudes= new ServicioSolicitudes();
    private Fecha fechaFull = new Fecha();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            Solicitudes solicitud = (Solicitudes) jsonSolicitudes.leerDeJSON(request,Solicitudes.class);
            solicitud.setFechaSolicitada(fechaFull.traerFechaActual());
            servicioSolicitudes.guardarNuevaSolicitud(ControladorLogin.conexion,solicitud);
            jsonSolicitudes.enviarComoJSON(response,solicitud);
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            try {
                var lista = servicioSolicitudes.traerTodasSolicitudes(ControladorLogin.conexion);
                jsonSolicitudes.enviarComoJSON(response,lista);
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        String [] splits = pathInfo.split("/");
        if(splits.length==3){//solicitudes de un paciente por estado
            String estado = splits[1];
            int idPac = Integer.parseInt(splits[2]);
            try {
                var lista = servicioSolicitudes.traerSolicitudesPendientesPorId(ControladorLogin.conexion,estado,idPac);
                if(lista.size()==0){
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                jsonSolicitudes.enviarComoJSON(response,lista);
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(splits.length==2){//ultima solicitud hecha por el paciente
            int idPaciente = Integer.parseInt(splits[1]);
                try {
                    var idConsulta=servicioSolicitudes.traerUltimaSolicitudHecha(ControladorLogin.conexion,idPaciente);
                    jsonSolicitudes.enviarComoJSON(response,idConsulta);
                    response.setStatus(HttpServletResponse.SC_OK);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }


    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);return;
        }
        String[] splits = pathInfo.split("/");
        int idSolicitud= Integer.parseInt(splits[1]);
        servicioSolicitudes.eliminarSolicitud(ControladorLogin.conexion,idSolicitud);
    }
}

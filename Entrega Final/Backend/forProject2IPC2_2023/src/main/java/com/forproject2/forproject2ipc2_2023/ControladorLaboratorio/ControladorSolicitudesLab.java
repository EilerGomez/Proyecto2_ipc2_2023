package com.forproject2.forproject2ipc2_2023.ControladorLaboratorio;

import com.forproject2.forproject2ipc2_2023.Controladores.ControladorLogin;
import com.forproject2.forproject2ipc2_2023.ServiciosForLaboratorio.ServicioSolicitudesPendientes;
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

@WebServlet(name = "ServletSolicitudeslab", urlPatterns = "/ServletSolicitudeslab/*")
public class ControladorSolicitudesLab extends HttpServlet {
    private UtilidadesJSON jsonSoliLab = new UtilidadesJSON();
    private ServicioSolicitudesPendientes servicioSolicitudesPendientes= new ServicioSolicitudesPendientes();

    private Fecha fechaFull = new Fecha();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String[] splits = pathInfo.split("/");
        //servlet/estado/idlab

        if(splits.length==3){
            String estado = splits[1];
            int idLab = Integer.parseInt(splits[2]);
            try {
                var lista = servicioSolicitudesPendientes.traerSolicitudesPorEstado(ControladorLogin.conexion,estado,idLab);
                if(lista.size()==0){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                jsonSoliLab.enviarComoJSON(response,lista);
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            Solicitudes solicitud = (Solicitudes) jsonSoliLab.leerDeJSON(request, Solicitudes.class);
            solicitud.setFechaFinalizada(fechaFull.traerFechaActual());
            solicitud.setEstado("FINALIZADA");
            servicioSolicitudesPendientes.actualizarSolicitud(ControladorLogin.conexion,solicitud);
            jsonSoliLab.enviarComoJSON(response,solicitud);
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
    }
}

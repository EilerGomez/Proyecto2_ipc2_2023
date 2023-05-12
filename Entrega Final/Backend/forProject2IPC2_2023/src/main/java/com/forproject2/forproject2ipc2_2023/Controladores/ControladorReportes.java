package com.forproject2.forproject2ipc2_2023.Controladores;

import com.forproject2.forproject2ipc2_2023.servicios.ServicioReportes;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletReportes", urlPatterns = {"/ServletReportes/*"})
public class ControladorReportes extends HttpServlet {
    private ServicioReportes servicioReportes=new ServicioReportes();
    private UtilidadesJSON jsonRep = new UtilidadesJSON();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] splits = pathInfo.split("/");
        //servlet../1 --> el reporte 1 es para saldo actual, 2 para top 5 pacientes, 3 top 5 especialidades
        String reporte = splits[1];
        if(splits.length==4){
            if(reporte.equals("1")){
                String userName = splits[2];
                String area = splits[3];
                try {
                    var medico =servicioReportes.verSaldoMedico(ControladorLogin.conexion,userName,area);
                    if(medico==null){
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                        return;
                    }
                    jsonRep.enviarComoJSON(response,medico);
                    response.setStatus(HttpServletResponse.SC_OK);
                    return;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        if(reporte.equals("2")){
            String desde = splits[2];
            String hasta = splits[3];
            int idMedico = Integer.parseInt((splits[4]));

            try {
                var lista = servicioReportes.traerReporteTop5PacientesmayorIngreso(ControladorLogin.conexion,desde,hasta, idMedico);
                if(lista.size()==0){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                jsonRep.enviarComoJSON(response,lista);
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if(reporte.equals("3")){
            String desde = splits[2];
            String hasta = splits[3];
            int idMedico = Integer.parseInt((splits[4]));
            try {
                var lista = servicioReportes.traerReporteTop5EspecialidadesMayorIngreso(ControladorLogin.conexion,desde,hasta, idMedico);
                if(lista.size()==0){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                jsonRep.enviarComoJSON(response,lista);
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

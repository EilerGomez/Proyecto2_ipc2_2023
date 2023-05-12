package com.forproject2.forproject2ipc2_2023.ControladorLaboratorio;

import com.forproject2.forproject2ipc2_2023.Controladores.ControladorLogin;
import com.forproject2.forproject2ipc2_2023.ServiciosForLaboratorio.ServicioReportesLaboratorio;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletReportesLab", urlPatterns = "/ServletReportesLab/*")
public class ControladorReportesLaboratorio extends HttpServlet {
    private UtilidadesJSON jsonReportes = new UtilidadesJSON();
    private ServicioReportesLaboratorio servicioReportesLaboratorio = new ServicioReportesLaboratorio();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String[] splits = pathInfo.split("/");
        //servlet/reporte/desde/hasta/idLab
        //reporte: 1 son los pacientes, 2 son los examenes
        if(splits.length==5){
            String asunto = splits[1];
            String desde = splits[2];
            String hasta = splits[3];
            int idLab = Integer.parseInt(splits[4]);
            if(asunto.equals("1")){
                try {
                    var lista = servicioReportesLaboratorio.traer5PacientesConMayorIngreso(ControladorLogin.conexion,desde,hasta, idLab);
                    if(lista.size()==0){
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                        return;
                    }
                    jsonReportes.enviarComoJSON(response,lista);
                    response.setStatus(HttpServletResponse.SC_OK);
                    return;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else if(asunto.equals("2")){
                try {
                    var lista = servicioReportesLaboratorio.traer5ExamenesConMayorIngreso(ControladorLogin.conexion,desde,hasta,idLab);
                    if(lista.size()==0){
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                        return;
                    }
                    jsonReportes.enviarComoJSON(response,lista);
                    response.setStatus(HttpServletResponse.SC_OK);
                    return;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

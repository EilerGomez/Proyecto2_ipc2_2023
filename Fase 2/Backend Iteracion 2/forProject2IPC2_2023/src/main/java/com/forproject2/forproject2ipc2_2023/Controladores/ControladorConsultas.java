package com.forproject2.forproject2ipc2_2023.Controladores;

import com.forproject2.forproject2ipc2_2023.modelo.Consultas.Consultas;
import com.forproject2.forproject2ipc2_2023.modelo.Fecha.Fecha;
import com.forproject2.forproject2ipc2_2023.modelo.cobroApp.CobroApicacion;
import com.forproject2.forproject2ipc2_2023.servicios.ServicioConsultas;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;

@WebServlet(name = "ServletConsultas", urlPatterns = {"/ServletConsultas/*"})
public class ControladorConsultas extends HttpServlet {
    private ServicioConsultas servicioConsultas;
    private UtilidadesJSON jsonCons;
    private Fecha fechaFull;

    public ControladorConsultas(){
        servicioConsultas= new ServicioConsultas();
        jsonCons = new UtilidadesJSON();
        fechaFull= new Fecha();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String[] splits = pathInfo.split("/");
        if (splits.length == 3) {
            String estado = (splits[2]);
            int idMedico = Integer.parseInt(splits[1]);
            try {
                if (servicioConsultas.consultasPorEstado(ControladorLogin.conexion, estado, idMedico).size() == 0) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                var lista =servicioConsultas.consultasPorEstado(ControladorLogin.conexion, estado, idMedico);
                response.setStatus(HttpServletResponse.SC_OK);
                jsonCons.enviarComoJSON(response,lista );;
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(splits.length==4){
            String estado = splits[2];
            int idMedico = Integer.parseInt(splits[1]);
            String dia = splits[3];
            if(dia.equals("0")){
                dia=enviarDiaActual(fechaFull.traerFechaActual());
            }
            System.out.println(estado + ", " + idMedico + ", " + dia);
            try {
                if(servicioConsultas.consultasPendientesPorDia(ControladorLogin.conexion,estado,idMedico,dia).size()==0){
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                var lista = servicioConsultas.consultasPendientesPorDia(ControladorLogin.conexion,estado,idMedico,dia);
                response.setStatus(HttpServletResponse.SC_OK);
                jsonCons.enviarComoJSON(response,lista);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(splits.length==2){
            int idMedico = Integer.parseInt(splits[1]);
            try {
                if(servicioConsultas.traerTodasConsultas(ControladorLogin.conexion,idMedico).size()==0){
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                var lista = servicioConsultas.traerTodasConsultas(ControladorLogin.conexion,idMedico);
                response.setStatus(HttpServletResponse.SC_OK);
                jsonCons.enviarComoJSON(response,lista);
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
            Consultas consulta = (Consultas) jsonCons.leerDeJSON(request, Consultas.class);
            servicioConsultas.actualizarEstadoConsulta(ControladorLogin.conexion,consulta);
            response.setStatus(HttpServletResponse.SC_OK);
            jsonCons.enviarComoJSON(response,consulta);
            return;
        }
    }

    private String enviarDiaActual(String fecha){
        String[] splits = fecha.split("-");
        String mes=splits[1]; String dia=splits[2];
        if(splits[1].length()==1){
            mes="0"+splits[1];
        }
        if(splits[2].length()==1){
            dia="0"+splits[2];
        }
        String fechaHoy = splits[0]+"-"+mes+"-"+dia;
        return fechaHoy;
    }

}

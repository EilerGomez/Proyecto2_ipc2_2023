package com.forproject2.forproject2ipc2_2023.Controladores;

import com.forproject2.forproject2ipc2_2023.modelo.Horarios.Horarios;
import com.forproject2.forproject2ipc2_2023.modelo.cobroApp.CobroApicacion;
import com.forproject2.forproject2ipc2_2023.servicios.ServicioHorariosMedicos;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;

@WebServlet(name = "ServletHorarios", urlPatterns = {"/ServletHorarios/*"})
public class ControladorHorarriosMedicos extends HttpServlet {
    private UtilidadesJSON jsonHorario= new UtilidadesJSON();
    private ServicioHorariosMedicos servicioHorariosMedicos = new ServicioHorariosMedicos();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doGet(req, resp);
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String[] splits = pathInfo.split("/");
        if (splits.length!=2){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        int idMedico = Integer.parseInt(splits[1]);
        try {
            var lista = servicioHorariosMedicos.traerHorariosPorIdMedico(ControladorLogin.conexion,idMedico);
            if(lista.size()==0){
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }


            jsonHorario.enviarComoJSON(response,lista);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){ response.sendError(HttpServletResponse.SC_BAD_REQUEST);}

            String[] splits = pathInfo.split("/");
            int idMedico = Integer.parseInt(splits[1]);
            var horario = jsonHorario.leerDeJSON(request, Horarios.class);
            try {
                if(servicioHorariosMedicos.traerHorariosPorIdMedico(ControladorLogin.conexion,idMedico).size()<2){
                    System.out.println(servicioHorariosMedicos.hayTraslape(servicioHorariosMedicos.traerHorariosPorIdMedico(ControladorLogin.conexion,idMedico), (Horarios) horario));
                    if(servicioHorariosMedicos.hayTraslape(servicioHorariosMedicos.traerHorariosPorIdMedico(ControladorLogin.conexion,idMedico), (Horarios) horario)==false){
                        servicioHorariosMedicos.guardarHorarios_Medicos(ControladorLogin.conexion, (Horarios) horario);
                        jsonHorario.enviarComoJSON(response,horario);
                        response.setStatus(HttpServletResponse.SC_OK);
                        return;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // super.doPut(req, resp);
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            Horarios horario = (Horarios) jsonHorario.leerDeJSON(request, Horarios.class);

            try {
                if(servicioHorariosMedicos.actualizarSinTraslape(servicioHorariosMedicos.traerHorariosPorIdMedico(ControladorLogin.conexion,horario.getIdMedico()), horario,horario.getIdHorario())==false){
                    servicioHorariosMedicos.editarHorarrios(ControladorLogin.conexion,  verificarHoras(horario));
                    jsonHorario.enviarComoJSON(response,horario);
                    response.setStatus(HttpServletResponse.SC_OK);
                    return;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
    private Horarios verificarHoras(Horarios horario){
        String [] hs = horario.getHoraSalida().split(":");
        String [] he = horario.getHoraEntrada().split(":");
        if(hs.length==2){
            horario.setHoraSalida(horario.getHoraSalida()+":00");
        }
        if(he.length==2){
            horario.setHoraEntrada(horario.getHoraEntrada()+":00");
        }
        System.out.println(horario.getHoraEntrada());
        System.out.println(horario.getHoraSalida());
        return horario;
    }

}

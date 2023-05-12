package com.forproject2.forproject2ipc2_2023.Controladores;

import com.forproject2.forproject2ipc2_2023.modelo.Especialidades_medicos.Especialidades_Medicos;
import com.forproject2.forproject2ipc2_2023.modelo.ExamenesLaboratorios.ExamenesLaboratorios;
import com.forproject2.forproject2ipc2_2023.servicios.ServicioExamenesLaboratorio;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletExamenesLaboratorio", urlPatterns = {"/ServletExamenesLaboratorio/*"})

public class ControladorExamenesLaboratorio extends HttpServlet {
    private UtilidadesJSON jsonExamenesLab;
    private ServicioExamenesLaboratorio servicioExamenesLaboratorio;

    public ControladorExamenesLaboratorio(){
        jsonExamenesLab = new UtilidadesJSON();
        servicioExamenesLaboratorio= new ServicioExamenesLaboratorio();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doGet(req, resp);
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            try {
                response.setStatus(HttpServletResponse.SC_OK);
                jsonExamenesLab.enviarComoJSON(response,servicioExamenesLaboratorio.traerTodosLosExamenesLaboratorios(ControladorLogin.conexion));
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        String[] splits = pathInfo.split("/");

        if(splits.length==2){
            //servlet/estado
            String estado = splits[1];
            try {

                if(servicioExamenesLaboratorio.traerListaExamenesLabPorEstado(ControladorLogin.conexion,estado).size()==0){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                var lista= servicioExamenesLaboratorio.traerListaExamenesLabPorEstado(ControladorLogin.conexion,estado);
                jsonExamenesLab.enviarComoJSON(response, lista);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(splits.length==3){
            //SERVLET/estado/idlaboratorio
            String estado = splits[1];
            int idlaboratorio = Integer.parseInt(splits[2]);
            try {
                var listaExamenes = servicioExamenesLaboratorio.traerExamenesLabPorID(ControladorLogin.conexion, idlaboratorio, estado);
                if(listaExamenes.size()==0){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                jsonExamenesLab.enviarComoJSON(response,listaExamenes);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            var examLab =  jsonExamenesLab.leerDeJSON(request, ExamenesLaboratorios.class);
            servicioExamenesLaboratorio.actualizarEstadoExamenLaboratorio(ControladorLogin.conexion, (ExamenesLaboratorios) examLab);
            response.setStatus(HttpServletResponse.SC_OK);
            jsonExamenesLab.enviarComoJSON(response,examLab);

        }
    }

    //este metodo es para el area de laboratorio, agregar nuevo examen para el

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            var examLab = jsonExamenesLab.leerDeJSON(request, ExamenesLaboratorios.class);
            servicioExamenesLaboratorio.agregarExamenlaboratorio(ControladorLogin.conexion, (ExamenesLaboratorios) examLab);
            response.setStatus(HttpServletResponse.SC_OK);
            jsonExamenesLab.enviarComoJSON(response, examLab);
        }
    }
}

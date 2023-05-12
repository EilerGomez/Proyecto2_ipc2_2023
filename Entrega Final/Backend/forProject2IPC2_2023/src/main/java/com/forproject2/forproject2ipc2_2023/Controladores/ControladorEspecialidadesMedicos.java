package com.forproject2.forproject2ipc2_2023.Controladores;

import com.forproject2.forproject2ipc2_2023.modelo.Especialidades_medicos.Especialidades_Medicos;
import com.forproject2.forproject2ipc2_2023.modelo.cobroApp.CobroApicacion;
import com.forproject2.forproject2ipc2_2023.servicios.ServicioEspecialidadesMedicas;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.naming.ldap.Control;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet (urlPatterns = {"/ServletEspecialidadesMedicas/*"})
public class ControladorEspecialidadesMedicos extends HttpServlet {
    private ServicioEspecialidadesMedicas servicioEspecialidadesMedicas;

    private UtilidadesJSON<Especialidades_Medicos> jsonEsp;
    public ControladorEspecialidadesMedicos(){
        servicioEspecialidadesMedicas= new ServicioEspecialidadesMedicas();
        jsonEsp = new UtilidadesJSON<Especialidades_Medicos>();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            try {
                response.setStatus(HttpServletResponse.SC_OK);
                jsonEsp.enviarComoJSON(response,servicioEspecialidadesMedicas.traerTodasEspecialidadesMedicas(ControladorLogin.conexion));
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        String[] splits = pathInfo.split("/");

        if(splits.length==2){
            String estado = splits[1];
            try {

                if(servicioEspecialidadesMedicas.traerEspecialidadesPorEstado(ControladorLogin.conexion,estado).size()==0){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                var lista= servicioEspecialidadesMedicas.traerEspecialidadesPorEstado(ControladorLogin.conexion,estado);
                jsonEsp.enviarComoJSON(response, lista);
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(splits.length!=3){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        int idMedico = Integer.parseInt(splits[2]);
        try {
            if(servicioEspecialidadesMedicas.traerEspecialidadesPoridMedico(ControladorLogin.conexion,idMedico).size()==0){
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            var listaN = servicioEspecialidadesMedicas.traerEspecialidadesPoridMedico(ControladorLogin.conexion,idMedico);
            jsonEsp.enviarComoJSON(response,listaN);
            response.setStatus(HttpServletResponse.SC_OK);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            var espMedic =jsonEsp.leerDeJSON(request, Especialidades_Medicos.class);
            servicioEspecialidadesMedicas.actualizarEstadoEspecialidadesMedicas(ControladorLogin.conexion,espMedic);
            response.setStatus(HttpServletResponse.SC_OK);
            jsonEsp.enviarComoJSON(response,espMedic);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            var espMedic = jsonEsp.leerDeJSON(request,Especialidades_Medicos.class);
            if(espMedic.getPrecio()<0){
                response.sendError(HttpServletResponse.SC_CONFLICT);
                return;
            }
            servicioEspecialidadesMedicas.agregarNuevaEspecialidadAMedico(ControladorLogin.conexion,espMedic);
            jsonEsp.enviarComoJSON(response,espMedic);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}

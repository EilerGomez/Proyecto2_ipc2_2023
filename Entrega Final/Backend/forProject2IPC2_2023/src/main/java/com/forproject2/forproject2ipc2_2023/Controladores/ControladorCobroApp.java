package com.forproject2.forproject2ipc2_2023.Controladores;

import com.forproject2.forproject2ipc2_2023.modelo.ExamenesLaboratorios.ExamenesLaboratorios;
import com.forproject2.forproject2ipc2_2023.modelo.Fecha.Fecha;
import com.forproject2.forproject2ipc2_2023.modelo.cobroApp.CobroApicacion;
import com.forproject2.forproject2ipc2_2023.servicios.ServicioCobroApp;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletCobroApp", urlPatterns = "/ServletCobroApp/*")

public class ControladorCobroApp extends HttpServlet {
    private UtilidadesJSON jsonCobroApp;
    private ServicioCobroApp servicioCobroApp;
    private Fecha fecha;
    public ControladorCobroApp(){
        jsonCobroApp = new UtilidadesJSON();
        servicioCobroApp = new ServicioCobroApp();
        fecha = new Fecha();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")){
            try {
                var historial=servicioCobroApp.traerTodoElHistorial(ControladorLogin.conexion);
                response.setStatus(HttpServletResponse.SC_OK);
                jsonCobroApp.enviarComoJSON(response,historial);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        String[] splits = pathInfo.split("/");

        if(splits.length!=2){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String ultimo = splits[1];
        if(ultimo.equals("0")){
            try {

                if(servicioCobroApp.traerUltimoCobro(ControladorLogin.conexion).equals(null)){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                var lista= servicioCobroApp.traerUltimoCobro(ControladorLogin.conexion);
                response.setStatus(HttpServletResponse.SC_OK);
                jsonCobroApp.enviarComoJSON(response, lista);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            CobroApicacion cobro = (CobroApicacion) jsonCobroApp.leerDeJSON(request, CobroApicacion.class);

            CobroApicacion nuevoCobro = new CobroApicacion(fecha.traerFechaActual(),cobro.getPorcentaje());
            System.out.println(nuevoCobro.getFechaModificacion());
            if(nuevoCobro.getPorcentaje()==0 || nuevoCobro.getPorcentaje()<0){
                response.sendError(HttpServletResponse.SC_CONFLICT);
                return;
            }
            servicioCobroApp.crearCobroApp(ControladorLogin.conexion, nuevoCobro);
            try {
                jsonCobroApp.enviarComoJSON(response,servicioCobroApp.traerUltimoCobro(ControladorLogin.conexion));
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

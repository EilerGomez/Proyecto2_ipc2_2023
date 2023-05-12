package com.forproject2.forproject2ipc2_2023.ControladorPaciente;

import com.forproject2.forproject2ipc2_2023.Controladores.ControladorLogin;
import com.forproject2.forproject2ipc2_2023.ServiciosForPacientes.ServicioBuscarLaboratorios;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletBuscarLaboratorios", urlPatterns = "/ServletBuscarLaboratorios/*")
public class ControladorBuscarLaboratorios extends HttpServlet {
    private ServicioBuscarLaboratorios servicioBuscarLaboratorios = new ServicioBuscarLaboratorios();
    private UtilidadesJSON jsonBuscarLab = new UtilidadesJSON();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String [] split = pathInfo.split("/");
        String nombre = split[1];
        if(nombre.equals("todos")){
            try {
                var lista = servicioBuscarLaboratorios.traerTodosLaboratorios(ControladorLogin.conexion);
                jsonBuscarLab.enviarComoJSON(response,lista);
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                var lab = servicioBuscarLaboratorios.traerLaboratoriosPorNombre(ControladorLogin.conexion, nombre);
                if(lab.size()==0){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                jsonBuscarLab.enviarComoJSON(response,lab);
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

package com.forproject2.forproject2ipc2_2023.ControladorPaciente;

import com.forproject2.forproject2ipc2_2023.Controladores.ControladorLogin;
import com.forproject2.forproject2ipc2_2023.ServiciosForPacientes.ServicioBuscarMedicosEoN;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletBuscarMedicos", urlPatterns = "/ServletBuscarMedicos/*")
public class ControladorBuscarMedicos extends HttpServlet {
    private UtilidadesJSON jsonBuscMedic = new UtilidadesJSON();
    private ServicioBuscarMedicosEoN servicioBuscarMedicosEoN = new ServicioBuscarMedicosEoN();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String [] splits = pathInfo.split("/");
        //servlet/1/123  1 representa que la busqueda es por especialidad, 123 es la especialdiad
        //servlet/2/Ramon 2 representa que la busqueda es por nombre ramon es el nombre del medico

        if(splits.length!=3){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if(splits[1].equals("1")){
            int idEspecialidad = Integer.parseInt(splits[2]);
            System.out.println(idEspecialidad);
            try {
                var lista = servicioBuscarMedicosEoN.traerMedicosporEspecialidad(ControladorLogin.conexion,idEspecialidad);

                if(lista.size()==0){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                jsonBuscMedic.enviarComoJSON(response,lista);
                response.setStatus(HttpServletResponse.SC_OK);
                return;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if(splits[1].equals("2")){
            String nombreMedico = splits[2];
            System.out.println(nombreMedico);
            try {
                if(servicioBuscarMedicosEoN.traerMedicosPorNombre(ControladorLogin.conexion,nombreMedico).size()==0){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                var lista = servicioBuscarMedicosEoN.traerMedicosPorNombre(ControladorLogin.conexion, nombreMedico);
                jsonBuscMedic.enviarComoJSON(response,lista);
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

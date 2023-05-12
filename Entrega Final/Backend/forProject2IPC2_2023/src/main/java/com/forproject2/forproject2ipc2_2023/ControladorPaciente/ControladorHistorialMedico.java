package com.forproject2.forproject2ipc2_2023.ControladorPaciente;

import com.forproject2.forproject2ipc2_2023.Controladores.ControladorLogin;
import com.forproject2.forproject2ipc2_2023.ServiciosForPacientes.ServicioHistorialMedico;
import com.forproject2.forproject2ipc2_2023.modelo.Examenes.Examenes;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServletHistorialMedicoPaciente", urlPatterns = "/ServletHistorialMedicoPaciente/*")
public class ControladorHistorialMedico extends HttpServlet {

    private UtilidadesJSON jsonHistMedic = new UtilidadesJSON();
    private ServicioHistorialMedico servicioHistorialMedico = new ServicioHistorialMedico();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String[] splits = pathInfo.split("/");
        if(splits.length==5){
            //servlet/asunto/paciente/desde/hasta     asunto 1 son consultas, 2 son examenes
            String asunto = splits[1];
            int idPaciente = Integer.parseInt(splits[2]);
            String desde = splits[3];
            String hasta = splits[4];
            System.out.println(asunto + idPaciente+desde + hasta);
            if(asunto.equals("1")){//son las consultas
                System.out.println("va aqui");
                try {
                    var lista = servicioHistorialMedico.traerConsultasDePaciente(ControladorLogin.conexion,idPaciente,desde,hasta);
                    if(lista.size()==0){
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                        return;
                    }
                    jsonHistMedic.enviarComoJSON(response,lista);
                    response.setStatus(HttpServletResponse.SC_OK);
                    return;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else if(asunto.equals("2")){
                List<Examenes> lista = null;
                try {
                    lista = servicioHistorialMedico.traerExamenesDePaciente(ControladorLogin.conexion,idPaciente,desde,hasta);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if(lista.size()==0){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                jsonHistMedic.enviarComoJSON(response,lista);
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            }
        }
    }
}

package com.forproject2.forproject2ipc2_2023.ControladorPaciente;

import com.forproject2.forproject2ipc2_2023.Controladores.ControladorLogin;
import com.forproject2.forproject2ipc2_2023.ServiciosForPacientes.ServicioVerExamenesRealizados;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletVerExamenesRealizadosP", urlPatterns = "/ServletVerExamenesRealizadosP/*")
public class ControladorVerExamenesRealizados extends HttpServlet {
    private UtilidadesJSON jsonExRea= new UtilidadesJSON();
    private ServicioVerExamenesRealizados servicioVerExamenesRealizados= new ServicioVerExamenesRealizados();


    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String[] splits = pathInfo.split("/");
        //servlet/idPaciente/desde/hasta/tipo
        if(splits.length==5){
            int idPaciente = Integer.parseInt(splits[1]);
            String desde= splits[2];
            String hasta = splits[3];
            String tipo = splits[4];
            var lista = servicioVerExamenesRealizados.traerExamenesPrFechaYTipo(ControladorLogin.conexion,idPaciente,desde,hasta,tipo);
            if(lista.size()==0){
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            jsonExRea.enviarComoJSON(response,lista);
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
    }
}

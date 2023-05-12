package com.forproject2.forproject2ipc2_2023.ControladorLaboratorio;

import com.forproject2.forproject2ipc2_2023.Controladores.ControladorLogin;
import com.forproject2.forproject2ipc2_2023.ServiciosForLaboratorio.ServicioDatosDeLaboratorio;
import com.forproject2.forproject2ipc2_2023.utilidades.UtilidadesJSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletDatosLaboratorio", urlPatterns = "/ServletDatosLaboratorio/*")
public class ControladorDatosLaboratorio extends HttpServlet {
    private UtilidadesJSON jsonDatosLab = new UtilidadesJSON();

    private ServicioDatosDeLaboratorio servicioDatosDeLaboratorio =new ServicioDatosDeLaboratorio();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String[] splits = pathInfo.split("/");
        if(splits.length==2){
            //servlet/idLab
            int idLab = Integer.parseInt(splits[1]);
            try {
                var lab = servicioDatosDeLaboratorio.traerDatoslaboratorio(ControladorLogin.conexion,idLab);
                jsonDatosLab.enviarComoJSON(response,lab);
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

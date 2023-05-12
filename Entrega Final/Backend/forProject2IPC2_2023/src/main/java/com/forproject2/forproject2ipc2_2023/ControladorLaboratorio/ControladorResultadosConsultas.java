package com.forproject2.forproject2ipc2_2023.ControladorLaboratorio;

import com.forproject2.forproject2ipc2_2023.Controladores.ControladorLogin;
import com.forproject2.forproject2ipc2_2023.ServiciosForLaboratorio.ServicioResultadosConsultas;
import com.forproject2.forproject2ipc2_2023.modelo.ResultadosConsultas.ResultadosConsulta;
import com.forproject2.forproject2ipc2_2023.modelo.ResultadosSolicitud.ResultadosSolicitud;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
@MultipartConfig
@WebServlet(name = "ServletResultadoConsulta", urlPatterns = "/ServletResultadoConsulta/*")
public class ControladorResultadosConsultas extends HttpServlet {
    private ServicioResultadosConsultas servicioResultadosConsultas= new ServicioResultadosConsultas();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/pdf");
        byte[] b = null;
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {


        }
        System.out.println("Entrando en el get");
        String[] splits = pathInfo.split("/");
        if (splits.length == 2) {
            //servlet/idConsulta
            int idConsulta = Integer.parseInt(splits[1]);

            try {
                servicioResultadosConsultas.traerArchivo(ControladorLogin.conexion,idConsulta);
                while (ControladorLogin.conexion.getResultSet().next()) {

                    b = ControladorLogin.conexion.getResultSet().getBytes(2);
                    InputStream bos = new ByteArrayInputStream(b);

                    int tamanoInput = bos.available();
                    byte[] datosPDF = new byte[tamanoInput];
                    bos.read(datosPDF, 0, tamanoInput);

                    response.getOutputStream().write(datosPDF);
                    bos.close();
                    System.out.println("terminando");

                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo= request.getPathInfo();
        if(pathInfo==null || pathInfo.equals("/")) {


        }
        System.out.println("Entrando en el post");
        String [] splits = pathInfo.split("/");
        if(splits.length==2){
            int idConsulta=Integer.parseInt(splits[1]);
            ResultadosConsulta resultado = new ResultadosConsulta();
            resultado.setIdConsulta(idConsulta);
            InputStream inputStream = null;
            try {
                Part filePart = request.getPart("archivoEntrada");
                if (filePart.getSize() > 0) {
                    System.out.println(filePart.getName());
                    System.out.println(filePart.getSize());
                    System.out.println(filePart.getContentType());
                    inputStream = filePart.getInputStream();
                }
                try{
                    if (inputStream != null) {
                        resultado.setDatosArchivo(inputStream);
                    }
                } catch (Exception ex) {
                    System.out.println("textos: "+ex.getMessage());
                }
                System.out.println(resultado.getIdConsulta());
                servicioResultadosConsultas.insertarResultado(ControladorLogin.conexion,resultado);
            } catch (Exception ex) {
                System.out.println("fichero: "+ex.getMessage());
            }
        }
    }
}

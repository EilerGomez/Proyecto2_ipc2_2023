package com.forproject2.forproject2ipc2_2023.ControladoresReportes_Todos;

import com.forproject2.forproject2ipc2_2023.Controladores.ControladorLogin;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@WebServlet (name = "RAdmin", urlPatterns = "/RAdmin/*")
public class ControladorReporteAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resources = request.getServletContext().getRealPath("/Reports/");
        System.out.println(resources);

        try (InputStream inputStream = new FileInputStream(resources + "HistorialCobroApp(admin).jasper");){
            response.setContentType("application/pdf");
            response.addHeader("Content-disposition", "attachment; filename=students.pdf");

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);

            Map<String, Object> params = new HashMap<>();
            params.put("nameAdmin", "Eiler");

            // Llenar el reporte con los datos y parámetros
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ControladorLogin.conexion.getConnection());

            // Exportar el reporte a PDF y escribirlo en la respuesta HTTP
            OutputStream out = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);

            out.flush();
            out.close();
        } catch (IOException | JRException e) {
            e.printStackTrace(System.out);
            throw new RuntimeException(e);
        }

    }
}

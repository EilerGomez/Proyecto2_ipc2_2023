package com.forproject2.forproject2ipc2_2023.ServiciosForLaboratorio;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.DatosForLaboratorio.DatosReportesLab;
import com.forproject2.forproject2ipc2_2023.modelo.ReportesLaboratorio.ReporteTop5ExamenesMayorIngreso;
import com.forproject2.forproject2ipc2_2023.modelo.ReportesLaboratorio.ReporteTop5PacientesMayorIngreso;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioReportesLaboratorio {
    private DatosReportesLab reportesLab = new DatosReportesLab();

    public List<ReporteTop5PacientesMayorIngreso> traer5PacientesConMayorIngreso(Conexion conexion, String desde, String hasta, int idLab) throws SQLException {
        List<ReporteTop5PacientesMayorIngreso> lista = new ArrayList<>();
        reportesLab.traerTop5Pacientes(conexion,desde,hasta,idLab);
        while(conexion.getResultSet().next()){
            var paciente = new ReporteTop5PacientesMayorIngreso(conexion.getResultSet().getString(1),conexion.getResultSet().getInt(2),
                    conexion.getResultSet().getDouble(3));
            lista.add(paciente);
        }
        return lista;
    }

    public List<ReporteTop5ExamenesMayorIngreso>traer5ExamenesConMayorIngreso (Conexion conexion, String desde, String hasta, int idlab) throws SQLException {
        List<ReporteTop5ExamenesMayorIngreso> lista = new ArrayList<>();
        reportesLab.traerTop5Examenes(conexion,desde,hasta,idlab);
        while(conexion.getResultSet().next()){
            var examen = new ReporteTop5ExamenesMayorIngreso(conexion.getResultSet().getString(1),conexion.getResultSet().getInt(2),
                    conexion.getResultSet().getDouble(3));
            lista.add(examen);
        }
        return lista;
    }


}

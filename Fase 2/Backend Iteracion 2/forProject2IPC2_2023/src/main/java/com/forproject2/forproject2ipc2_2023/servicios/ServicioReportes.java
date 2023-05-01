package com.forproject2.forproject2ipc2_2023.servicios;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.datos.DatosReportes;
import com.forproject2.forproject2ipc2_2023.modelo.Reportes.ReporteTop5Especialidades;
import com.forproject2.forproject2ipc2_2023.modelo.Reportes.ReporteTop5Pacintes;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Personas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioReportes {
    private ServicioLogin servicioLogin = new ServicioLogin();
    private final DatosReportes datosReporteTop5Pacientes = new DatosReportes();

    public Personas verSaldoMedico(Conexion conexion, String userName, String area) throws SQLException {
        return servicioLogin.leerUsers(conexion, userName, area);
    }

    private List<ReporteTop5Pacintes> convertirALista(Conexion conexion, String desde, String hasta, int idMedico) throws SQLException {
        List<ReporteTop5Pacintes> lista = new ArrayList<>();
        datosReporteTop5Pacientes.traerPacientes(conexion, desde, hasta, idMedico);
        while (conexion.getResultSet().next()) {
            var paciente = new ReporteTop5Pacintes(conexion.getResultSet().getString(1), conexion.getResultSet().getInt(2),
                    conexion.getResultSet().getDouble(3));
            lista.add(paciente);
        }
        return lista;
    }

    public List<ReporteTop5Especialidades> convertirEspList(Conexion conexion, String desde, String hasta, int idMedico) throws SQLException {
        List<ReporteTop5Especialidades> lista = new ArrayList<>();
        datosReporteTop5Pacientes.traerEspecialidades(conexion,desde,hasta, idMedico);
        while(conexion.getResultSet().next()){
            var esp = new ReporteTop5Especialidades(conexion.getResultSet().getString(1),conexion.getResultSet().getInt(2),
                    conexion.getResultSet().getDouble(3));
            lista.add(esp);
        }
        return lista;
    }


    public List<ReporteTop5Pacintes> traerReporteTop5PacientesmayorIngreso(Conexion conexion, String desde, String hasta, int idMedico) throws SQLException {
        return convertirALista(conexion, desde, hasta, idMedico);
    }

    public  List<ReporteTop5Especialidades> traerReporteTop5EspecialidadesMayorIngreso(Conexion conexion, String desde, String hasta, int idMedico) throws SQLException {
        return convertirEspList(conexion,desde,hasta, idMedico);
    }


}

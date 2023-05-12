package com.forproject2.forproject2ipc2_2023.ServiciosForPacientes;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.DatosForPaciente.DatosSolicitudes;
import com.forproject2.forproject2ipc2_2023.modelo.Solicitudes.Solicitudes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioSolicitudes {
    private DatosSolicitudes datosSolicitudes= new DatosSolicitudes();


    public List<Solicitudes> traerTodasSolicitudes(Conexion conexion) throws SQLException {
        List<Solicitudes> lista = new ArrayList<>();
        datosSolicitudes.traerTodasSolicitudes(conexion);
        while(conexion.getResultSet().next()){
            var solicitud = new Solicitudes(conexion.getResultSet().getInt(1),conexion.getResultSet().getInt(3),
                    conexion.getResultSet().getInt(2),conexion.getResultSet().getDouble(4), conexion.getResultSet().getString(5),
                    conexion.getResultSet().getString(6),conexion.getResultSet().getString(7));
            lista.add(solicitud);
        }
        return lista;
    }

    public void guardarNuevaSolicitud(Conexion conexion, Solicitudes solicitud){
        datosSolicitudes.guardarNuevaSolicitud(conexion,solicitud);
    }

    public void eliminarSolicitud(Conexion conexion,int idSolicitud){
        datosSolicitudes.eliminarSolicitud(conexion,idSolicitud);
    }

    public List<Solicitudes> traerSolicitudesPendientesPorId(Conexion conexion, String estado, int idpaciente) throws SQLException {
        return traerTodasSolicitudes(conexion).stream().filter(s-> s.getEstado().equals(estado)&&s.getIdPaciente()==idpaciente).collect(Collectors.toList());
    }

    public int traerUltimaSolicitudHecha(Conexion conexion, int idPaciente) throws SQLException {
        datosSolicitudes.traerUltimaSolicitud(conexion, idPaciente);
        int idConsulta=0;
        while(conexion.getResultSet().next()){
            idConsulta=conexion.getResultSet().getInt(1);
        }
        return idConsulta;
    }




}

package com.forproject2.forproject2ipc2_2023.ServiciosForLaboratorio;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.DatosForPaciente.DatosSolicitudes;
import com.forproject2.forproject2ipc2_2023.modelo.Solicitudes.Solicitudes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioSolicitudesPendientes {
    private DatosSolicitudes datosSolicitudes = new DatosSolicitudes();
    public List<Solicitudes> traerSolicitudesPorEstado(Conexion conexion, String stado, int idLab) throws SQLException {
        if(stado.equals("0")){
            return traerTodasSolicitudes(conexion).stream().filter(s->s.getIdLaboratorio()==idLab).collect(Collectors.toList());
        }else{
            return traerTodasSolicitudes(conexion).stream().filter(s->s.getIdLaboratorio()==idLab && s.getEstado().equals(stado)).collect(Collectors.toList());
        }
    }

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

    public void actualizarSolicitud(Conexion conexion, Solicitudes solicitud){
        datosSolicitudes.actualizarSolicitud(conexion,solicitud);
    }

}

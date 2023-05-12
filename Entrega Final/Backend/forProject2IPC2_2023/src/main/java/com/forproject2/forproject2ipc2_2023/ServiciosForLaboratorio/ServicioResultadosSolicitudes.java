package com.forproject2.forproject2ipc2_2023.ServiciosForLaboratorio;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.DatosForLaboratorio.DatosResultadosSolicitud;
import com.forproject2.forproject2ipc2_2023.modelo.ResultadosSolicitud.ResultadosSolicitud;

import java.sql.ResultSet;

public class ServicioResultadosSolicitudes {
    private DatosResultadosSolicitud datosResultadosSolicitud= new DatosResultadosSolicitud();

    public void  nuevoresultado(Conexion conexion, ResultadosSolicitud resultado){
    datosResultadosSolicitud.insertarNuevoResultado(conexion,resultado);
    }

    public void traerArchivo(Conexion conexion, int idSolicitud){
        datosResultadosSolicitud.traerArchivos(conexion,idSolicitud);
    }


}

package com.forproject2.forproject2ipc2_2023.ServiciosForLaboratorio;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.DatosForLaboratorio.DatosResultadosConsulta;
import com.forproject2.forproject2ipc2_2023.modelo.ResultadosConsultas.ResultadosConsulta;

public class ServicioResultadosConsultas {
    private DatosResultadosConsulta datosResultadosConsulta= new DatosResultadosConsulta();

    public void insertarResultado(Conexion conexion, ResultadosConsulta resultadp){
        datosResultadosConsulta.insertarNuevoResultado(conexion,resultadp);
    }

    public void traerArchivo(Conexion conexion, int idConsulta){
        datosResultadosConsulta.traerArchivos(conexion,idConsulta);
    }


}

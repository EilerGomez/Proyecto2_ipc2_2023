package com.forproject2.forproject2ipc2_2023.servicios;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.datos.DatosEspecialidades;
import com.forproject2.forproject2ipc2_2023.modelo.Especialidades.Especialidades;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioEspecialidades {
    private DatosEspecialidades datosEspecialidades;
    public ServicioEspecialidades(){
        datosEspecialidades=new DatosEspecialidades();
    }
    public List<Especialidades> traerTodasLasEspecialidades(Conexion conexion) throws SQLException {
        return  convertirALista(conexion);
    }
    private List<Especialidades> convertirALista(Conexion conexion) throws SQLException {
        List<Especialidades> listaEspecialidades = new ArrayList<>();
        datosEspecialidades.traerTodasEspecialidades(conexion);
        while(conexion.getResultSet().next()){
            var Esp = new Especialidades(conexion.getResultSet().getInt(1),conexion.getResultSet().getString(2),conexion.getResultSet().getString(3));
        listaEspecialidades.add(Esp);
        }
        return listaEspecialidades;
    }



}

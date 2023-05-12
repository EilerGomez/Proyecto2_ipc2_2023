package com.forproject2.forproject2ipc2_2023.servicios;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.datos.DatosExamenesLaboratorio;
import com.forproject2.forproject2ipc2_2023.modelo.ExamenesLaboratorios.ExamenesLaboratorios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioExamenesLaboratorio {
    private DatosExamenesLaboratorio datosExamenesLaboratorio;
    public ServicioExamenesLaboratorio(){
        datosExamenesLaboratorio=new DatosExamenesLaboratorio();
    }

    public List<ExamenesLaboratorios> traerListaExamenesLabPorEstado(Conexion conexion, String estado) throws SQLException {
        return  traerTodosLosExamenesLaboratorios(conexion).stream().filter(s -> s.getEstado().equals(estado)).collect(Collectors.toList());
    }

    public List<ExamenesLaboratorios> traerTodosLosExamenesLaboratorios(Conexion conexion) throws SQLException {
        datosExamenesLaboratorio.traerExamenesLaboratorio(conexion);
        List<ExamenesLaboratorios> examenes = new ArrayList<>();
        while(conexion.getResultSet().next()){
            ExamenesLaboratorios newExamen = new ExamenesLaboratorios(conexion.getResultSet().getInt(1),conexion.getResultSet().getInt(2),
                    conexion.getResultSet().getString(3),conexion.getResultSet().getDouble(4),conexion.getResultSet().getString(5));
            examenes.add(newExamen);
        }
        return examenes;
    }

    public void actualizarEstadoExamenLaboratorio(Conexion conexion, ExamenesLaboratorios examen){
        datosExamenesLaboratorio.actualizarEstadoExamenLaboratorio(conexion,examen);
    }

    public List<ExamenesLaboratorios> traerExamenesLabPorID(Conexion conexion, int idLaboratorio, String estado) throws SQLException {
        //ESTO ES PARA EL AREA DE LABORATORIO
        if(estado.equals("0")){
            return traerTodosLosExamenesLaboratorios(conexion).stream().filter(s-> s.getIdLaboratorio()==idLaboratorio).collect(Collectors.toList());
        }else{
            return traerTodosLosExamenesLaboratorios(conexion).stream().filter(s-> s.getIdLaboratorio()==idLaboratorio && s.getEstado().equals(estado)).collect(Collectors.toList());
        }
    }

    public void agregarExamenlaboratorio(Conexion conexion, ExamenesLaboratorios examen){
        datosExamenesLaboratorio.agregarExamenLaboratorio(conexion,examen);
    }


}

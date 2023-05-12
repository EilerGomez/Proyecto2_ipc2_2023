package com.forproject2.forproject2ipc2_2023.ServiciosForPacientes;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.DatosForPaciente.DatosLaboratorios;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Laboratorios.Laboratorios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioBuscarLaboratorios {
    private DatosLaboratorios datosLaboratorios= new DatosLaboratorios();

    public List<Laboratorios> traerTodosLaboratorios(Conexion conexion) throws SQLException {
        List<Laboratorios> lista = new ArrayList<>();
        datosLaboratorios.traerTodosLaboratorios(conexion);
        while(conexion.getResultSet().next()){
            var laboratorio = new Laboratorios(conexion.getResultSet().getInt(1),conexion.getResultSet().getString(2),
                    conexion.getResultSet().getString(3),conexion.getResultSet().getString(4),conexion.getResultSet().getString(8),
                    conexion.getResultSet().getString(9),conexion.getResultSet().getDouble(10),conexion.getResultSet().getString(5),
                    conexion.getResultSet().getString(6),conexion.getResultSet().getString(7));
            lista.add(laboratorio);
        }
        return lista;
    }

    public List<Laboratorios> traerLaboratoriosPorNombre(Conexion conexion, String nombre) throws SQLException {
        return traerTodosLaboratorios(conexion).stream().filter(s-> s.getNombre().equals(nombre)).collect(Collectors.toList());
    }


}

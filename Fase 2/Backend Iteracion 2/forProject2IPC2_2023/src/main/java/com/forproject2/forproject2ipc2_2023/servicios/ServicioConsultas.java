package com.forproject2.forproject2ipc2_2023.servicios;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.datos.DatosConsultas;
import com.forproject2.forproject2ipc2_2023.modelo.Consultas.Consultas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioConsultas {
    private DatosConsultas datosConsultas= new DatosConsultas();
    public List<Consultas> consultasPorEstado(Conexion conexion, String estado, int idMedico) throws SQLException {
        return convertirAConsultas(conexion).stream().filter(s -> s.getIdMedico()==idMedico && s.getEstado().equals(estado)).collect(Collectors.toList());
    }

    public List<Consultas> consultasPendientesPorDia(Conexion conexion, String estado, int idMedico, String dia) throws SQLException {
        return convertirAConsultas(conexion).stream().filter(s -> s.getIdMedico()==idMedico && s.getEstado().equals(estado) && s.getFechaCreacion().equals(dia)).collect(Collectors.toList());
    }
    public List<Consultas> traerTodasConsultas(Conexion conexion, int idMedico) throws SQLException {
        return convertirAConsultas(conexion).stream().filter(s -> s.getIdMedico()==idMedico).collect(Collectors.toList());
    }

    private List<Consultas> convertirAConsultas(Conexion conexion) throws SQLException {
        datosConsultas.traerTodasConsultas(conexion);
        List<Consultas> listaConsultas = new ArrayList<>();
        while(conexion.getResultSet().next()){
            var newCon = new Consultas(conexion.getResultSet().getInt(1),conexion.getResultSet().getInt(3),conexion.getResultSet().getInt(4),
                    conexion.getResultSet().getInt(2),conexion.getResultSet().getDouble(6),conexion.getResultSet().getString(8),
                    conexion.getResultSet().getString(7),conexion.getResultSet().getDouble(5),conexion.getResultSet().getString(10),
                    conexion.getResultSet().getString(9));
            listaConsultas.add(newCon);
            System.out.println(newCon.getIdMedico() + ", " + newCon.getEstado());

        }
        return listaConsultas;
    }

    public void actualizarEstadoConsulta(Conexion conexion, Consultas consulta){
        datosConsultas.actualizarConsulta(conexion,consulta);
    }

}

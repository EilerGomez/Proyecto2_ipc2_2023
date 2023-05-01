package com.forproject2.forproject2ipc2_2023.servicios;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.datos.DatosExamenesConsulta;
import com.forproject2.forproject2ipc2_2023.modelo.Examenes_Consulta.Examenes_Consulta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioExamenesConsulta {
    private DatosExamenesConsulta datosExamenesConsulta = new DatosExamenesConsulta();

    public List<Examenes_Consulta> traerExamenesDeConsulta(Conexion conexion, int idConsulta) throws SQLException {
        return convertirALista(conexion,idConsulta);
    }

    private List<Examenes_Consulta> convertirALista(Conexion conexion, int idConsulta) throws SQLException {
        List<Examenes_Consulta> listaExamenes = new ArrayList<>();
        datosExamenesConsulta.traerExamenesDeConsulta(conexion,idConsulta);
        while(conexion.getResultSet().next()){
            var examen = new Examenes_Consulta(conexion.getResultSet().getInt(1),conexion.getResultSet().getString(2),
                    conexion.getResultSet().getInt(3));
            listaExamenes.add(examen);
        }
        return listaExamenes;
    }

    public void guardarExamenesConsulta(Conexion conexion, int idExamen, int idConsulta){
        datosExamenesConsulta.agregarExamenConsulta(conexion,idConsulta,idExamen);
    }
}

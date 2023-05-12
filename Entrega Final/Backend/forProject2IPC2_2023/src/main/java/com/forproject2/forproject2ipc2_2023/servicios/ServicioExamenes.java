package com.forproject2.forproject2ipc2_2023.servicios;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.datos.DatosExamenes;
import com.forproject2.forproject2ipc2_2023.modelo.Examenes.Examenes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioExamenes {
    private DatosExamenes datosExamenes = new DatosExamenes();
    public List<Examenes> traerTodosExamenes(Conexion conexion) throws SQLException {
        return leerExamenes(conexion);
    }

    private List<Examenes> leerExamenes(Conexion conexion) throws SQLException {
        List<Examenes> listaExamenes = new ArrayList<>();
        datosExamenes.traerTodosLosExamenes(conexion);
        while (conexion.getResultSet().next()){
            Examenes newExamen = new Examenes(conexion.getResultSet().getInt(1),conexion.getResultSet().getString(2),
                    conexion.getResultSet().getString(3));
            listaExamenes.add(newExamen);
        }
        return listaExamenes;
    }

}

package com.forproject2.forproject2ipc2_2023.servicios;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.datos.DatosCobroApp;
import com.forproject2.forproject2ipc2_2023.modelo.cobroApp.CobroApicacion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioCobroApp {
    private DatosCobroApp datosCobroApp;
    public ServicioCobroApp(){
        datosCobroApp=new DatosCobroApp();
    }
    public void crearCobroApp(Conexion conexion, CobroApicacion cobroApicacion) {
        datosCobroApp.crearCobroApp(conexion,cobroApicacion);
    }
    public List<CobroApicacion> traerTodoElHistorial(Conexion conexion) throws SQLException {
        datosCobroApp.traerTodoElHistorial(conexion);
        List<CobroApicacion> historialCobro = new ArrayList<>();
        while (conexion.getResultSet().next()){
            CobroApicacion newCobro = new CobroApicacion(conexion.getResultSet().getInt(1),conexion.getResultSet().getString(2),conexion.getResultSet().getDouble(3));
            historialCobro.add(newCobro);
        }
        return historialCobro;
    }

    public CobroApicacion traerUltimoCobro(Conexion conexion) throws SQLException {
        return traerTodoElHistorial(conexion).get(traerTodoElHistorial(conexion).size()-1);
    }

}

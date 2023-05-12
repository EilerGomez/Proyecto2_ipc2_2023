package com.forproject2.forproject2ipc2_2023.ServiciosForLaboratorio;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.DatosForLaboratorio.DatosLaboratorio;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Laboratorios.Laboratorios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioDatosDeLaboratorio {
    private DatosLaboratorio datosLaboratorio = new DatosLaboratorio();

    public Laboratorios traerDatoslaboratorio(Conexion conexion, int idLab) throws SQLException {
        return leerLabs(conexion).stream().filter(s-> s.getIdUsuario()==idLab).collect(Collectors.toList()).get(0);
    }

    private List<Laboratorios> leerLabs(Conexion conexion) throws SQLException {
        List<Laboratorios> lista = new ArrayList<>();
        datosLaboratorio.traerLaboratorio(conexion);
        while(conexion.getResultSet().next()){
            Laboratorios lab = new Laboratorios(conexion.getResultSet().getInt(1),conexion.getResultSet().getString(2),
                    conexion.getResultSet().getString(3),conexion.getResultSet().getString(4),conexion.getResultSet().getString(8),
                    conexion.getResultSet().getString(9),conexion.getResultSet().getDouble(10),conexion.getResultSet().getString(5),
                    conexion.getResultSet().getString(6),conexion.getResultSet().getString(7));
            lista.add(lab);
        }
        return lista;
    }

}

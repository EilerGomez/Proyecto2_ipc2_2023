package com.forproject2.forproject2ipc2_2023.ServiciosForPacientes;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.DatosForPaciente.DatosRecargarSaldoPaciente;
import com.forproject2.forproject2ipc2_2023.modelo.Fecha.Fecha;
import com.forproject2.forproject2ipc2_2023.modelo.HistorialRecargas.HistorialRecargas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioRecargarSaldopaciente {
    private Fecha fecha= new Fecha();
    private DatosRecargarSaldoPaciente datosRecargarSaldoPaciente = new DatosRecargarSaldoPaciente();

    public void recargarSaldo(Conexion conexion, int idPaiciente, int recarga)
    {
        datosRecargarSaldoPaciente.actualizarSaldopaciente(conexion,idPaiciente,recarga);
        guardarHistorialRecargas(conexion,idPaiciente,recarga);
    }

    public List<HistorialRecargas> historialrecargasPorId(Conexion conexion, int idPaciente) throws SQLException {
        return convertirLista(conexion).stream().filter(s-> s.getIdPaciente()==idPaciente).collect(Collectors.toList());
    }

    private void guardarHistorialRecargas(Conexion conexion, int idpaciente, int recarga)
    {
        var Rec = new HistorialRecargas(0,idpaciente,recarga,fecha.traerFechaActual());
        datosRecargarSaldoPaciente.agregarhistorialRecargas(conexion,Rec);
    }

    private List<HistorialRecargas> convertirLista(Conexion conexion) throws SQLException {
        List<HistorialRecargas> lista = new ArrayList<>();
        datosRecargarSaldoPaciente.traerHistorialMedico(conexion);
        while(conexion.getResultSet().next()){
            var historial = new HistorialRecargas(conexion.getResultSet().getInt(1),conexion.getResultSet().getInt(2),
                    conexion.getResultSet().getInt(3),conexion.getResultSet().getString(4));
            lista.add(historial);
        }
        return lista;
    }
}

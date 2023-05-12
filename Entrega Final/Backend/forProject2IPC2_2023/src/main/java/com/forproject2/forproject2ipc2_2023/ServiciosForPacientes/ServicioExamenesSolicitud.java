package com.forproject2.forproject2ipc2_2023.ServiciosForPacientes;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.DatosForPaciente.DatosExamenesSolicitudes;
import com.forproject2.forproject2ipc2_2023.datos.DatosConsultas;
import com.forproject2.forproject2ipc2_2023.modelo.Consultas.Consultas;
import com.forproject2.forproject2ipc2_2023.modelo.ExamenesSolicitud.ExamenesSolicitud;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioExamenesSolicitud {
    private DatosExamenesSolicitudes datosExamenesSolicitudes = new DatosExamenesSolicitudes();
    private DatosConsultas datosConsultas = new DatosConsultas();

    public boolean guardarExamenSolicitud(Conexion conexion, ExamenesSolicitud examenesSolicitud, double porcentaje, int idLab, int idPaciente){
        return actualizarSaldoPaciente(conexion,examenesSolicitud,porcentaje,idLab,idPaciente);
    }

    public List<ExamenesSolicitud> traerExamenesSolicitudPorId(Conexion conexion, int idSolicitud) throws SQLException {
        return listarExamenesSolicitud(conexion).stream().filter(s-> s.getIdSolicitud()==idSolicitud).collect(Collectors.toList());
    }



    private  List<ExamenesSolicitud> listarExamenesSolicitud(Conexion conexion) throws SQLException {
        datosExamenesSolicitudes.traerTodosExamenesSolicitud(conexion);
        List<ExamenesSolicitud> lista = new ArrayList<>();
        while(conexion.getResultSet().next()){
            var examenSoli = new ExamenesSolicitud(conexion.getResultSet().getInt(1),conexion.getResultSet().getInt(2),
                   conexion.getResultSet().getString(4), conexion.getResultSet().getDouble(3));
            lista.add(examenSoli);
        }
        return lista;
    }
    private boolean actualizarSaldoPaciente(Conexion conexion, ExamenesSolicitud examSol, double porcentajeapp,int idLab, int idPaciente){
        boolean guardado = false;
        if(datosConsultas.traerSaldoPaciente(conexion,idPaciente)-examSol.getPrecio()<0){
            System.out.println("No hay suficiente dinero");
            guardado=false;
        }else{
            datosExamenesSolicitudes.guardarNuevoExamen(conexion,examSol);
            actualizarSaldoAdmin(conexion, porcentajeapp,examSol);
            actualizarSaldoLab(conexion,examSol,porcentajeapp, idLab);
            datosConsultas.actualizarSaldoPaciente(conexion,examSol.getPrecio(),idPaciente);
            guardado=true;
        }
        return guardado;
    }
    private void actualizarSaldoAdmin(Conexion conexion, double porcentajeApp, ExamenesSolicitud examen){
        double saldo = examen.getPrecio()*porcentajeApp;
        datosConsultas.actualizarSaldoAdmin(conexion,Math.round(saldo*100.0)/100.0);
    }
    private void actualizarSaldoLab(Conexion conexion, ExamenesSolicitud examen, double porcentaje, int idLab){
        double ganado = examen.getPrecio() - examen.getPrecio()*porcentaje;
        datosExamenesSolicitudes.actualizarSaldoLab(conexion,Math.round(ganado*100.0)/100.0,idLab);
    }


}

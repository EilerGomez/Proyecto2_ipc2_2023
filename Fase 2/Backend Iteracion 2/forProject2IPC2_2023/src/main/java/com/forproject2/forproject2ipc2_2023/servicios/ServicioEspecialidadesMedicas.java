package com.forproject2.forproject2ipc2_2023.servicios;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.datos.DatosEspecialidadesMedicos;
import com.forproject2.forproject2ipc2_2023.modelo.Especialidades_medicos.Especialidades_Medicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioEspecialidadesMedicas {
    DatosEspecialidadesMedicos datosEspecialidadesMedicos = new DatosEspecialidadesMedicos();

    public List<Especialidades_Medicos> traerEspecialidadesPorEstado(Conexion conexion, String estado) throws SQLException {

        return traerTodasEspecialidadesMedicas(conexion).stream().filter(s -> s.getEstado().equals(estado)).collect(Collectors.toList());
    }

    public List<Especialidades_Medicos> traerEspecialidadesPoridMedico(Conexion conexion, int idMedico) throws SQLException {
        return traerTodasEspecialidadesMedicas(conexion).stream().filter(s -> s.getIdMedico()==idMedico).collect(Collectors.toList());
    }

    public List<Especialidades_Medicos> traerTodasEspecialidadesMedicas(Conexion conexion) throws SQLException {
        List<Especialidades_Medicos> listaEspecialidadesMedicas = new ArrayList<>();
        datosEspecialidadesMedicos.traerTodasEspecialidades(conexion);
        while (conexion.getResultSet().next()) {
            var nuevaEsp = new Especialidades_Medicos(conexion.getResultSet().getInt(1), conexion.getResultSet().getInt(2),
                    conexion.getResultSet().getString(3),conexion.getResultSet().getString(4),conexion.getResultSet().getDouble(5),
                    conexion.getResultSet().getString(6));
            listaEspecialidadesMedicas.add(nuevaEsp);
        }
        if (conexion.getResultSet() == null) {
            return null;
        } else {
            return listaEspecialidadesMedicas;
        }
    }

    public void actualizarEstadoEspecialidadesMedicas(Conexion conexion, Especialidades_Medicos especialidad) {
        datosEspecialidadesMedicos.actualizarEspecialidadesMedicas(conexion, especialidad);
    }

    public void agregarNuevaEspecialidadAMedico(Conexion conexion, Especialidades_Medicos especialidades) {
        datosEspecialidadesMedicos.agregarNuevaEspecialidadAMedico(conexion, especialidades);
    }

     /*List<Especialidades_Medicos> listaEspecialidadesMedicas = new ArrayList<>();
        datosEspecialidadesMedicos.traerEspecialidadesPorEstado(conexion, especialidad);
        while (conexion.getResultSet().next()) {
            var nuevaEsp = new Especialidades_Medicos(conexion.getResultSet().getInt(1), conexion.getResultSet().getInt(2),
                    conexion.getResultSet().getDouble(3), conexion.getResultSet().getString(4));
            listaEspecialidadesMedicas.add(nuevaEsp);
        }*/

}

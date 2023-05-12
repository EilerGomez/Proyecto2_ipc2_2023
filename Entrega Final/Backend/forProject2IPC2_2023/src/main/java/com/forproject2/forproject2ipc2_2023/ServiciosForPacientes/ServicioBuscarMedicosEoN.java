package com.forproject2.forproject2ipc2_2023.ServiciosForPacientes;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.DatosForPaciente.DatosBuscarMedicos;
import com.forproject2.forproject2ipc2_2023.modelo.Especialidades_medicos.Especialidades_Medicos;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Medicos.Medicos;
import com.forproject2.forproject2ipc2_2023.servicios.ServicioEspecialidadesMedicas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioBuscarMedicosEoN {
    private DatosBuscarMedicos datosBuscarMedicos = new DatosBuscarMedicos();
    private ServicioEspecialidadesMedicas servicioEspecialidadesMedicas= new ServicioEspecialidadesMedicas();

    private List<Medicos> ListarTodosmedicos(Conexion conexion) throws SQLException {
        List<Medicos> listaMedicos = new ArrayList<>();
        datosBuscarMedicos.traerTodosMedicos(conexion);
        while(conexion.getResultSet().next()){
            var medico = new Medicos(conexion.getResultSet().getInt(1),conexion.getResultSet().getString(2),
                    conexion.getResultSet().getString(3), conexion.getResultSet().getString(4),conexion.getResultSet().getString(8),
                    conexion.getResultSet().getString(9), conexion.getResultSet().getDouble(10),conexion.getResultSet().getString(5),
                    conexion.getResultSet().getString(6),conexion.getResultSet().getString(7));
            listaMedicos.add(medico);
        }
        return listaMedicos;
    }



    public List<Medicos> traerMedicosporEspecialidad(Conexion conexion, int especialidad) throws SQLException {
        datosBuscarMedicos.traerMeeicos_Espedialidades(conexion, especialidad);
        List<Medicos> medicos = new ArrayList<>();
        while(conexion.getResultSet().next()){
            var medico = new Medicos(conexion.getResultSet().getInt(1),conexion.getResultSet().getString(2),
                    conexion.getResultSet().getString(3), conexion.getResultSet().getString(4),conexion.getResultSet().getString(8),
                    conexion.getResultSet().getString(9), conexion.getResultSet().getDouble(10),conexion.getResultSet().getString(5),
                    conexion.getResultSet().getString(6),conexion.getResultSet().getString(7));
            medicos.add(medico);
        }
        return medicos;
    }

    public List<Medicos> traerMedicosPorNombre(Conexion conexion, String nombre) throws SQLException {
        return ListarTodosmedicos(conexion).stream().filter(s->s.getNombre().equals(nombre)).collect(Collectors.toList());
    }



}

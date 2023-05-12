package com.forproject2.forproject2ipc2_2023.servicios;

import com.forproject2.forproject2ipc2_2023.Conexion.Conexion;
import com.forproject2.forproject2ipc2_2023.datos.DatosHorariosMedicos;
import com.forproject2.forproject2ipc2_2023.modelo.Horarios.Horarios;
import com.mysql.cj.conf.HostInfo;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioHorariosMedicos {

    private DatosHorariosMedicos datosHorariosMedicos;

    public ServicioHorariosMedicos() {
        datosHorariosMedicos = new DatosHorariosMedicos();
    }

    public void guardarHorarios_Medicos(Conexion conexion, Horarios horarioMedico) {
        datosHorariosMedicos.guardarHorarios_Medicos(horarioMedico, conexion);

    }

    public List<Horarios> traerHorariosPorIdMedico(Conexion conexion, int idMedico) throws SQLException {
        leerHoras(convertirALista(conexion).stream().filter(s -> s.getIdMedico() == idMedico).collect(Collectors.toList()));
        return convertirALista(conexion).stream().filter(s -> s.getIdMedico() == idMedico).collect(Collectors.toList());
    }

    public List<Horarios> convertirALista(Conexion conexion) throws SQLException {
        List<Horarios> horarrios = new ArrayList<>();
        datosHorariosMedicos.traerTodosLosHorarrios(conexion);
        while (conexion.getResultSet().next()) {
            var horario = new Horarios(conexion.getResultSet().getInt(1), conexion.getResultSet().getInt(2),
                    conexion.getResultSet().getString(3), conexion.getResultSet().getString(4));
            horarrios.add(horario);
        }
        return horarrios;
    }

    public void editarHorarrios(Conexion conexion, Horarios horario){
        datosHorariosMedicos.editarHorarrios(conexion,horario);
    }

    public void leerHoras(List<Horarios> horarios){
        for(Horarios horario: horarios){

            String[] horaSalid= horario.getHoraSalida().split(":");
            String[] horaEntrad = horario.getHoraEntrada().split(":");
            Time horaEntrada = new Time(Integer.parseInt(horaEntrad[0]),Integer.parseInt(horaEntrad[1]),Integer.parseInt(horaEntrad[2]));
            Time horaSalida = new Time(Integer.parseInt(horaSalid[0]),Integer.parseInt(horaSalid[1]),Integer.parseInt(horaSalid[2]));

            System.out.println(horaEntrada.getHours());
        }

    }

    public boolean hayTraslape(List<Horarios> horarios, Horarios nuevoH){
        boolean haytraslape=false;
        Horarios horario = horarios.get(0);

            int minutosEH=horario.horaEntrada().getHours()*60 + horario.horaEntrada().getMinutes();
            int minutosSH = horario.horaSalida().getHours()*60 + horario.horaSalida().getMinutes();

            int minutosEN = nuevoH.horaEntrada().getHours()*60 + nuevoH.horaEntrada().getMinutes();
            int minutosSN = nuevoH.horaSalida().getHours()*60 + nuevoH.horaSalida().getMinutes();
            if(haytraslape ==false){
                if((minutosEN<minutosEH && minutosSN<minutosEH)||(minutosEN>minutosSH && minutosSN>minutosSH)){
                    haytraslape=false;
                }else{
                    haytraslape=true;
                }
            }

        return haytraslape;
    }

    public boolean actualizarSinTraslape(List<Horarios> horarios, Horarios nuevoH, int idH){
        List<Horarios> hor = horarios.stream().filter(s -> s.getIdHorario() != idH).collect(Collectors.toList());
        return hayTraslape(hor,nuevoH);
    }


}

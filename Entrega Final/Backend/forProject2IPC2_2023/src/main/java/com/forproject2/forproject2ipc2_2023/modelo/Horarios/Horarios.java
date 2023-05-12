package com.forproject2.forproject2ipc2_2023.modelo.Horarios;

import lombok.*;

import java.sql.Time;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Horarios {
    private int idHorario;
    private int idMedico;
    private String horaEntrada;
    private String horaSalida;

    public Horarios(int idMedico, String horaEntrada, String horaSalida){
        this.horaEntrada=horaEntrada;
        this.horaSalida=horaSalida;
        this.idMedico=idMedico;
    }

    public Time horaEntrada(){
        String[] horaEntrad = horaEntrada.split(":");
        Time horaEntrada = new Time(Integer.parseInt(horaEntrad[0]),Integer.parseInt(horaEntrad[1]),Integer.parseInt(horaEntrad[2]));
        return horaEntrada;
    }
    public Time horaSalida(){
        String[] horaSalid= horaSalida.split(":");
        Time horaSalida = new Time(Integer.parseInt(horaSalid[0]),Integer.parseInt(horaSalid[1]),Integer.parseInt(horaSalid[2]));
        return horaSalida;
    }
}

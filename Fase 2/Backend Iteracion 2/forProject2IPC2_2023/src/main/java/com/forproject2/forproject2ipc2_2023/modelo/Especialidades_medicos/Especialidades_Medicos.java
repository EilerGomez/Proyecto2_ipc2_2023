package com.forproject2.forproject2ipc2_2023.modelo.Especialidades_medicos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Especialidades_Medicos {
    private int idEspecialidad;
    private int idMedico;
    private String nombreEspecialidad;
    private String nombreMedico;
    private double precio;
    private String estado;

    public Especialidades_Medicos(int idEspecialidad, int idMedico, double precio, String estado){
        this.idEspecialidad=idEspecialidad;
        this.idMedico=idMedico;
        this.precio=precio;
        this.estado=estado;
    }


}

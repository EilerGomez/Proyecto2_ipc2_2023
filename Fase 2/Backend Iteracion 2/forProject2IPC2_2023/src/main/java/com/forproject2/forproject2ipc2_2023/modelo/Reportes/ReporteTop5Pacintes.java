package com.forproject2.forproject2ipc2_2023.modelo.Reportes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
public class ReporteTop5Pacintes {
    private String nombrePaciente;
    private int cantidadConsultas;
    private double cantidadGenerado;


}

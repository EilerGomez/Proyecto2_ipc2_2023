package com.forproject2.forproject2ipc2_2023.modelo.ReportesLaboratorio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReporteTop5ExamenesMayorIngreso {
    private String nombreExamen;
    private int cantidadExamenes;
    private double cantidadGenerado;
}

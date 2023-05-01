package com.forproject2.forproject2ipc2_2023.modelo.cobroApp;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CobroApicacion {
    private int idCobro;
    private String fechaModificacion;
    private double porcentaje;

    public CobroApicacion(String fechaModificacion, double porcentaje) {
        this.fechaModificacion=fechaModificacion;
        this.porcentaje=porcentaje;
    }

}

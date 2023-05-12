package com.forproject2.forproject2ipc2_2023.modelo.ExamenesSolicitud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ExamenesSolicitud {
    private int idSolicitud;
    private int idExamen;
    private String nombreExamen;
    private double precio;

    public ExamenesSolicitud(int idSolicitud, int idExamen, double precio){
        this.idSolicitud=idSolicitud;
        this.idExamen=idExamen;
        this.precio=precio;
    }
}

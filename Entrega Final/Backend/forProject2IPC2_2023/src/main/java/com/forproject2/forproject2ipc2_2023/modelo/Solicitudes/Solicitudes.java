package com.forproject2.forproject2ipc2_2023.modelo.Solicitudes;

import com.forproject2.forproject2ipc2_2023.modelo.Examenes.Examenes;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Solicitudes {
    private int idSolicitud;
    private int idPaciente;
    private int idLaboratorio;
    private double porcentajeCobroApp;
    private String fechaSolicitada;
    private String fechaFinalizada;
    private List<Examenes> examenes;
    private String estado;

    public Solicitudes(int idSolicitud, int idPaciente, int idLaboratorio, double porcentajeCobroApp, String fechaSolicitada, String fechaFinalizada, String estado) {
        this.idSolicitud= idSolicitud;
        this.idPaciente= idPaciente;
        this.idLaboratorio= idLaboratorio;
        this.porcentajeCobroApp= porcentajeCobroApp;
        this.fechaSolicitada=fechaSolicitada;
        this.fechaFinalizada= fechaFinalizada;
        this.estado= estado;
    }

}

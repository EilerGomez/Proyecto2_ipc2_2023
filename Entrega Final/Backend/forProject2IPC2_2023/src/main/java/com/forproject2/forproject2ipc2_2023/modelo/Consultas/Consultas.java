package com.forproject2.forproject2ipc2_2023.modelo.Consultas;

import com.forproject2.forproject2ipc2_2023.modelo.Examenes.Examenes;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consultas {
    private int idConsulta;
    private int idPaciente;
    private int idMedico;
    private int idEspecialidad;
    private double porcentajeCobroApp;
    private List<Examenes> examenesSolicitados;
    private String fechaCreacion;
    private String fechaAgendada;
    private double precio;
    private String informe;
    private String estado;
    public Consultas(int idConsulta, int idPaciente, int idMedico, int idEspecialidad, double porcentajeCobroApp,
          String fechaCreacion, String fechaAgendada, double precio, String informe, String estado){
        this.idConsulta= idConsulta;
        this.idPaciente= idPaciente;
        this.idMedico= idMedico;
        this.idEspecialidad= idEspecialidad;
        this.porcentajeCobroApp= porcentajeCobroApp;
        this.fechaCreacion= fechaCreacion;
        this.fechaAgendada= fechaAgendada;
        this.precio=precio;
        this.informe= informe;
        this.estado= estado;

    }
}

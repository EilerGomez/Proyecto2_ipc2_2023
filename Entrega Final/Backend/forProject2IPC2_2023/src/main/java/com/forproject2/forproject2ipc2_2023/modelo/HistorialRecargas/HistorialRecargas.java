package com.forproject2.forproject2ipc2_2023.modelo.HistorialRecargas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistorialRecargas {
    private int id;
    private int idPaciente;
    private int recarga;
    private String fecha;
}

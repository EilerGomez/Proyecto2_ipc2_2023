package com.forproject2.forproject2ipc2_2023.modelo.ExamenesLaboratorios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ExamenesLaboratorios {
    private int idLaboratorio;
    private int idExamen;
    private String nombreExamen;
    private double precio;
    private String estado;

    public String imprimirAtributos(){
        return "Examenes de laboratorio:" + idLaboratorio + "\n, idExamen: "+idExamen+ "\n, nombre: "+nombreExamen +  "\n, precio: "+precio +
                "\n, estado: "+estado;
    }
}

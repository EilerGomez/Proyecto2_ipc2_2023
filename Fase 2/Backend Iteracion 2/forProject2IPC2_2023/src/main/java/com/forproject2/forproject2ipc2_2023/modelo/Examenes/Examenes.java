package com.forproject2.forproject2ipc2_2023.modelo.Examenes;

import lombok.*;

@Getter @Setter @ToString
 @NoArgsConstructor @AllArgsConstructor
@Builder
public class Examenes {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;

    public Examenes(int id, String nombre, String descripcion){
        this.id=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
    }
    public Examenes(int id, double precio){
        this.id=id;
        this.precio=precio;
    }
    public Examenes(int id){
        this.id=id;
    }
}

package com.forproject2.forproject2ipc2_2023.modelo.Especialidades;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Especialidades {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;

    public Especialidades(int id, String nombre, String descripcion){
        this.id=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
    }
    public Especialidades(int id, double precio){
        this.id=id;
        this.precio=precio;
    }
}

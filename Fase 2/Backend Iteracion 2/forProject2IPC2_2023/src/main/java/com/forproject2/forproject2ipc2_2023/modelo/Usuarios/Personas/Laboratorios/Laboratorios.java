package com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Laboratorios;

import com.forproject2.forproject2ipc2_2023.modelo.Examenes.Examenes;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Personas;
import lombok.*;

import java.util.List;




public class Laboratorios extends Personas {
    private List<Examenes> examenesLab;
    public Laboratorios(int idUsuario, String nombre,String userName, String password, String gmail, String fechaNacimiento, double saldo, String direccion, String cui, String telefono){
        super(idUsuario,nombre,userName, password,gmail,fechaNacimiento,saldo, direccion, cui, telefono);

    }
}

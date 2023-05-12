package com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Paciente;

import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Personas;
import lombok.*;

@Getter
@Setter
@ToString


public class Pacientes extends Personas {
    public Pacientes(int idUsuario, String nombre,String userName, String password, String gmail, String fechaNacimiento, double saldo, String direccion, String cui, String telefono){
        super(idUsuario,nombre,userName, password,gmail,fechaNacimiento,saldo, direccion, cui, telefono);
    }
}

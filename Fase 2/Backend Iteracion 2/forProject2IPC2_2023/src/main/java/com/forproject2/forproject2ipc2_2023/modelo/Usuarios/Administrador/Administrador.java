package com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Administrador;

import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Usuarios;
import lombok.*;

@NoArgsConstructor


public class Administrador extends Usuarios {
    public Administrador(int idUsuario, String nombre,String userName, String password, String gmail, String fechaNacimiento, double saldo){
        super(idUsuario,nombre,userName,password,gmail,fechaNacimiento,saldo);
    }


}

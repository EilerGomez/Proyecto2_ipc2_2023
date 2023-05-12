package com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas;

import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Usuarios;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Personas extends Usuarios {
    private String direccion;
    private String cui;
    private String telefono;

    public Personas(int idUsuario, String nombre, String userName, String password, String gmail, String fechaNacimiento, double saldo, String direccion, String cui, String telefono){
        super(idUsuario,nombre,userName, password,gmail,fechaNacimiento,saldo);
        this.cui=cui;
        this.direccion=direccion;
        this.telefono=telefono;
    }



}

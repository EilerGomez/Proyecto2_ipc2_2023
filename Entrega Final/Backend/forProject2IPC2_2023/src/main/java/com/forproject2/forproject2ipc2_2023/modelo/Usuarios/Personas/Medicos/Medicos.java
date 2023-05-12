package com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Medicos;
import java.util.List;

import com.forproject2.forproject2ipc2_2023.modelo.Especialidades.Especialidades;
import com.forproject2.forproject2ipc2_2023.modelo.Horarios.Horarios;
import com.forproject2.forproject2ipc2_2023.modelo.Usuarios.Personas.Personas;
import lombok.*;

@Getter
@Setter
@ToString

public class Medicos extends Personas {

    private List<Horarios> horariosMedicos;
    private List<Especialidades> especialidades;
    public Medicos(int idUsuario, String nombre,String userName, String password, String gmail, String fechaNacimiento, double saldo, String direccion, String cui, String telefono){
        super(idUsuario,nombre,userName, password,gmail,fechaNacimiento,saldo, direccion,cui,telefono);

    }


}

package com.forproject2.forproject2ipc2_2023.modelo.Usuarios;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Usuarios {
    private int idUsuario;
    private String nombre;
    private String userName;
    private String password;
    private String email;
    private String fechaNacimiento;
    private double saldo;
}

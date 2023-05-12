package com.forproject2.forproject2ipc2_2023.modelo.ResultadosConsultas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.InputStream;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultadosConsulta {
    private int idConsulta;
    private byte[] archivo;
    private InputStream datosArchivo;
}

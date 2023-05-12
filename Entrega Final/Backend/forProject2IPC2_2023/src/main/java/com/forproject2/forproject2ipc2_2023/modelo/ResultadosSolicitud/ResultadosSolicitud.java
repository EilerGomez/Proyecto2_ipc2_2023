package com.forproject2.forproject2ipc2_2023.modelo.ResultadosSolicitud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.InputStream;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultadosSolicitud {
    private int idSolicitud;
    private byte[] archivo;
    private InputStream datosArchivo;
}

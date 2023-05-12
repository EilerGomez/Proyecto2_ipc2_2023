package com.forproject2.forproject2ipc2_2023.modelo.Fecha;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fecha {
    public String traerFechaActual() {
        Calendar calendario = new GregorianCalendar();
        return String.valueOf(calendario.get(Calendar.YEAR)
                + "-" + String.valueOf(calendario.get(Calendar.MONTH) + 1)
                + "-" + String.valueOf(calendario.get(Calendar.DAY_OF_MONTH)));
    }
}

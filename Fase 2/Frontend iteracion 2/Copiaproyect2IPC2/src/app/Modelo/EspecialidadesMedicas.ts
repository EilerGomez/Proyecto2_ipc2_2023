export class EspecialidadesMedicas {
    idEspecialidad: number;
    idMedico: number;
    nombreEspecialidad: String;
    nombreMedico: String;
    precio: number;
    estado: String;

    constructor(idEspecialidad: number, idMedico: number,nombreEspecialidad: String, nombreMedico: String, precio: number, estado: String) {
        this.idMedico = idMedico;
        this.idEspecialidad = idEspecialidad;
        this.nombreEspecialidad=nombreEspecialidad;
        this.nombreMedico=nombreMedico;
        this.precio = precio;
        this.estado = estado;
    }
}
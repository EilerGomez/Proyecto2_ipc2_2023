export class HorariosMedicos {
    idHorario: number;
    idMedico: number;
    horaEntrada: String;
    horaSalida: String;

    constructor(idHorario: number,idMedico: number,horaEntrada: String,horaSalida: String) {

        this.idMedico=idMedico;
        this.idHorario=idHorario;
        this.horaEntrada=horaEntrada;
        this.horaSalida=horaSalida;
    }
}
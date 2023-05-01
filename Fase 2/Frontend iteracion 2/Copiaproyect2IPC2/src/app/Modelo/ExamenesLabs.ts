export class ExamenesLabs {
    idLaboratorio !: number;
    idExamen !: number;
    nombreExamen !: String;
    precio !: number;
    estado !: String;

    constructor(idLaboratorio:number, idExamen:number, nombreExamen:String, precio:number, estado:String){
        this.idLaboratorio=idLaboratorio;
        this.idExamen=idExamen;
        this.nombreExamen=nombreExamen;
        this.precio=precio;
        this.estado=estado;
    }
}
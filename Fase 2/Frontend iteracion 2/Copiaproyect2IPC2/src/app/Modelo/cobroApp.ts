export class cobroApp {
    idCobro: number;
    fechaModificacion: String;
    porcentaje: number;

     constructor(idCobro:number, fechaModificacion:String,porcentaje: number){
        this.idCobro=idCobro;
        this.fechaModificacion=fechaModificacion;
        this.porcentaje=porcentaje;
     }
}
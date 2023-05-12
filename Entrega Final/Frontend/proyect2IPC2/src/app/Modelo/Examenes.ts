export class Examenes {
    id!: number;
    nombre!: String;
    descripcion!: String;
    precio!: number;

    constructor(id: number,
        nombre: String,
        descripcion: String,
        precio: number){
            this.id=id;
            this.nombre=nombre;
            this.descripcion=descripcion;
            this.precio=precio;
        }
}
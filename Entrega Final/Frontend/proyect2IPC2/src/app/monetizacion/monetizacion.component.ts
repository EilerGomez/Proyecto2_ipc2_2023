import { Component, OnInit } from '@angular/core';
import { cobroApp } from '../Modelo/cobroApp';
import { ServiceCobroAppService } from '../Service/service-cobro-app.service'

@Component({
  selector: 'app-monetizacion',
  templateUrl: './monetizacion.component.html',
  styleUrls: ['./monetizacion.component.css']
})
export class MonetizacionComponent implements OnInit {

 
  cobroApp!: cobroApp;
  newConbro!: cobroApp;
  porcentaje!: number;

  constructor(private service: ServiceCobroAppService) { }

  ngOnInit() :void{
    this.service.getUltimoRegistro()
      .subscribe(data => {
        this.cobroApp = data;
      }, error => {
        console.log(error)
      });
  }

  guardarPorcentaje(cantidad: string) {
    this.porcentaje = parseFloat(cantidad);
    this.newConbro = new cobroApp(2, "asadasd", this.porcentaje);


    this.service.postNewPorcentaje(this.newConbro)
      .subscribe(data => {
        this.newConbro = data;
        this.cobroApp=data;
        console.log(this.newConbro.porcentaje);
      }, error => {
        console.log(error);
      });    
  }

  traerUltimo() {
    this.service.getUltimoRegistro()
      .subscribe(data => {
        this.cobroApp = data;
      }, error => {
        console.log(error)
      });
      
  }
}


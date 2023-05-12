import { Component } from '@angular/core';
import { Laboratorios } from 'src/app/Modelo/Laboratorio';
import {ServiceBuscarLaboratoriosService} from 'src/app/Service/service-buscar-laboratorios.service'


@Component({
  selector: 'app-buscar-laboratorios',
  templateUrl: './buscar-laboratorios.component.html',
  styleUrls: ['./buscar-laboratorios.component.css']
})
export class BuscarLaboratoriosComponent {

  constructor(private service:ServiceBuscarLaboratoriosService){

  }
  laboratorioAAgregar!:Laboratorios
  laboratorios!:Laboratorios[]

  buscar(nombrelab:String){
    this.service.getLaboratorios(nombrelab)
    .subscribe(data=>{
      this.laboratorios=data;
    }, error=>{
      console.log(error)
    });
  }
  mostrarAgregarExamenes:boolean=false

  SeleccionarMedico(lab:Laboratorios){
    this.laboratorioAAgregar=lab
    this.mostrarAgregarExamenes=true
  }
  recibirEvento(recibir:boolean){
    this.mostrarAgregarExamenes=recibir
  }
}

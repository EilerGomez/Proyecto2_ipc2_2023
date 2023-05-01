import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Consultas } from 'src/app/Modelo/Consultas';
import { ServiceConsultasService } from 'src/app/Service/service-consultas.service';

@Component({
  selector: 'app-finalizar-consulta',
  templateUrl: './finalizar-consulta.component.html',
  styleUrls: ['./finalizar-consulta.component.css']
})
export class FinalizarConsultaComponent {

  constructor(private service:ServiceConsultasService){}
  mostrarFinalizarConsulta:boolean=false
  @Input() consulta!:Consultas
  mostrarAlerta:boolean=false
  mostrarExito:boolean=false
  FinalizarConsulta(informe:String){
    if(informe=="" || informe==null){
      this.mostrarAlerta=true
    }else{
      this.mostrarAlerta=false;
      this.mostrarExito=true  
      this.consulta.estado="FINALIZADA";
      this.consulta.informe=informe;
      this.service.putConsultaInformeYEstado(this.consulta)
      .subscribe(data=>{
        console.log(JSON.stringify(data))
      },error=>{
        console.log(error)
      })
    }
  }
  @Output() mostrarTabl = new EventEmitter<boolean>();
  @Output() actualizarTabla= new EventEmitter();
  Volver(){
    this.mostrarTabl.emit(this.mostrarFinalizarConsulta);
    this.actualizarTabla.emit();
  }


}

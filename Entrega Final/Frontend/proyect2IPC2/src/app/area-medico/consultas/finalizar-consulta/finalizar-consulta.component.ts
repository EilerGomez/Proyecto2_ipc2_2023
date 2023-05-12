import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { Consultas } from 'src/app/Modelo/Consultas';
import { ExamenesConsultas } from 'src/app/Modelo/ExamenesConsultas';
import { ServiceConsultasService } from 'src/app/Service/service-consultas.service';
import { ServiceExamenesConsultasService } from 'src/app/Service/service-examenes-consultas.service';

@Component({
  selector: 'app-finalizar-consulta',
  templateUrl: './finalizar-consulta.component.html',
  styleUrls: ['./finalizar-consulta.component.css']
})
export class FinalizarConsultaComponent implements OnInit{

  constructor(private service:ServiceConsultasService,private serviceExamenesC:ServiceExamenesConsultasService){}
  ngOnInit(): void {
    this.serviceExamenesC.getExamenesConsultas(this.consulta.idConsulta)
    .subscribe(data=>{
      this.examenesConsulta=data;
    })
  }
  mostrarFinalizarConsulta:boolean=false
  @Input() consulta!:Consultas
  mostrarAlerta:boolean=false
  mostrarExito:boolean=false
  examenesConsulta!:ExamenesConsultas[]
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

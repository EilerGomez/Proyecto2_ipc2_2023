import { Component, OnInit } from '@angular/core';
import { Cons } from 'rxjs';
import { Consultas } from 'src/app/Modelo/Consultas';
import { Pacientes } from 'src/app/Modelo/Paciente';
import { ServiceConsultasPacientesService } from 'src/app/Service/service-consultas-pacientes.service';

@Component({
  selector: 'app-listar-consultas-agendadas',
  templateUrl: './listar-consultas-examen_pendiente.html',
  styleUrls: ['./listar-consultas-agendadas.component.css']
})
export class ListarConsultasExamenPendienteComponent implements OnInit{
  constructor(private servicioConsPac:ServiceConsultasPacientesService){}
  ngOnInit(): void {
    let stringUser = localStorage.getItem('usuario');
    let medic: Pacientes = stringUser ? JSON.parse(stringUser) : null;
    let idPaciente = medic ? medic.idUsuario : 0;
    this.servicioConsPac.getConsultasExamenPendiente(idPaciente)
    .subscribe(data=>{
      this.consultas=data;
    })
  }
  mostrarSubirResultados:boolean=false
  consultas!:Consultas[]
  consultaEdit!:Consultas

  NoMostrar(event:boolean){
    this.mostrarSubirResultados=event
    this.ngOnInit()
  }
  Seleccionar(consult:Consultas){
    this.consultaEdit=consult;
    this.mostrarSubirResultados=true
  }
}

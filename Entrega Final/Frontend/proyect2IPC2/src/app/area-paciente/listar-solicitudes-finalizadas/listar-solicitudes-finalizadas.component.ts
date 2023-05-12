import { Component, OnInit } from '@angular/core';
import { Pacientes } from 'src/app/Modelo/Paciente';
import { Solicitudes } from 'src/app/Modelo/Solicitudes';
import { ServiceSolicitudesService } from 'src/app/Service/service-solicitudes.service';

@Component({
  selector: 'app-listar-solicitudes-finalizadas',
  templateUrl: './listar-solicitudes-finalizadas.component.html',
  styleUrls: ['./listar-solicitudes-finalizadas.component.css']
})
export class ListarSolicitudesFinalizadasComponent implements OnInit{
  constructor(private serviceSolicitudes:ServiceSolicitudesService){}

  mostrarResultados:boolean=false
  
  solicitudes!:Solicitudes[]
  solicitudEdit!:Solicitudes
  ngOnInit(): void {
    let stringUser = localStorage.getItem('usuario');
    let medic: Pacientes = stringUser ? JSON.parse(stringUser) : null;
    let idPaciente = medic ? medic.idUsuario : 0;
    this.serviceSolicitudes.getSolicitudesFinalizadas(idPaciente)
    .subscribe(data=>{
      this.solicitudes=data
    })

  }

  Seleccionar(solicitud:Solicitudes){
    this.solicitudEdit=solicitud;
    this.mostrarResultados=true
  }

  mostrarResults(event:boolean){
    this.mostrarResultados=event;
  }
}

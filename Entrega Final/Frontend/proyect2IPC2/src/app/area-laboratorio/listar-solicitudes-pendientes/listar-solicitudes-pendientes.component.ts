import { Component, OnInit } from '@angular/core';
import { Laboratorios } from 'src/app/Modelo/Laboratorio';
import { Solicitudes } from 'src/app/Modelo/Solicitudes';
import { ServiceSolicitudesLABService } from 'src/app/Service/service-solicitudes-lab.service';

@Component({
  selector: 'app-listar-solicitudes-pendientes',
  templateUrl: './listar-solicitudes-pendientes.component.html',
  styleUrls: ['./listar-solicitudes-pendientes.component.css']
})
export class ListarSolicitudesPendientesComponent implements OnInit{
  constructor(private service:ServiceSolicitudesLABService){}
 
  solicitudes!:Solicitudes[]
  solicitudEdit!:Solicitudes
  mostrarAgregarResultados:boolean=false

  ngOnInit(): void {
    let stringUser = localStorage.getItem('usuario');
      let medic: Laboratorios = stringUser ? JSON.parse(stringUser) : null;
      let idLab = medic ? medic.idUsuario : 0;
    this.service.getSolicitudesPendientes(idLab)
    .subscribe(data=>{
      this.solicitudes=data
    })
  }

  Seleccionar(solicitud:Solicitudes){
    this.solicitudEdit=solicitud
    this.mostrarAgregarResultados=true
  }
  NoMostrarAgregarExamenes(mostrar:boolean){
    this.mostrarAgregarResultados=mostrar
    this.ngOnInit();
  }
}

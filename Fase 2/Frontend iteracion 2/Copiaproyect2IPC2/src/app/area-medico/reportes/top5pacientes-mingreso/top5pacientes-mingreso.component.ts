import { Component } from '@angular/core';
import { Medicos } from 'src/app/Modelo/Medicos';
import { Reporte5PacientesMIngreso } from 'src/app/Modelo/Reporte5PacientesMIngreso';
import { ServiceReportesService } from 'src/app/Service/service-reportes.service';

@Component({
  selector: 'app-top5pacientes-mingreso',
  templateUrl: './top5pacientes-mingreso.component.html',
  styleUrls: ['./top5pacientes-mingreso.component.css']
})
export class Top5pacientesMingresoComponent{
  constructor(private service:ServiceReportesService){}
  report5PMI!:Reporte5PacientesMIngreso[]
  mostrarTabla:boolean=false

  MostrarReporte(desde:String, hasta:String){
    let stringUser = localStorage.getItem('usuario');
    let medic: Medicos = stringUser ? JSON.parse(stringUser) : null;
    let idmedico = medic ? medic.idUsuario : 0;

    this.service.getTop5PacientesMIngreso(desde,hasta,idmedico)
    .subscribe(data=>{
      this.report5PMI=data;
    },error=>{
      console.log(error)
    })
    this.mostrarTabla=true;
  }
}

import { Component } from '@angular/core';
import { Medicos } from 'src/app/Modelo/Medicos';
import { Reporte5EspecialidadesMIngreso } from 'src/app/Modelo/Reporte5EspecialidadesMIngreso';
import { ServiceReportesService } from 'src/app/Service/service-reportes.service';

@Component({
  selector: 'app-top5especialidades-mingreso',
  templateUrl: './top5especialidades-mingreso.component.html',
  styleUrls: ['./top5especialidades-mingreso.component.css']
})
export class Top5especialidadesMingresoComponent {
   constructor(private service:ServiceReportesService){}
  report5EMI!:Reporte5EspecialidadesMIngreso[]
  mostrarTabla:boolean=false



  MostrarReporte(desde:String, hasta:String){
    let stringUser = localStorage.getItem('usuario');
    let medic: Medicos = stringUser ? JSON.parse(stringUser) : null;
    let idmedico = medic ? medic.idUsuario : 0;

    this.service.getTop5EspecialidadesMIngreso(desde,hasta,idmedico)
    .subscribe(data=>{
      this.report5EMI=data;
    }, error=>{
      console.log(error)
    })

    this.mostrarTabla=true
  }
}

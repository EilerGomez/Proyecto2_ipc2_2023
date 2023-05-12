import { Component, OnInit } from '@angular/core';
import { Examenes } from 'src/app/Modelo/Examenes';
import { ExamenesLabs } from 'src/app/Modelo/ExamenesLabs';
import { Laboratorios } from 'src/app/Modelo/Laboratorio';
import { ServiceExamenesService } from 'src/app/Service/service-examenes.service';

@Component({
  selector: 'app-nuevo-examen',
  templateUrl: './nuevo-examen.component.html',
  styleUrls: ['./nuevo-examen.component.css']
})
export class NuevoExamenComponent implements OnInit{

  constructor(private servicioExamenes: ServiceExamenesService){}
  ngOnInit(): void {
    this.servicioExamenes.getExamenesDeLaApp()
    .subscribe(data=>{
      this.examenes=data
    })
  }
  examenes!:Examenes[]
  mostrarAletar:boolean=false


  solicitarExamen(idExamen:string, precio:string){
    this.mostrarAletar=false
    if(precio==null||precio==""){
      this.mostrarAletar=true
    }else{
      let stringUser = localStorage.getItem('usuario');
      let medic: Laboratorios = stringUser ? JSON.parse(stringUser) : null;
      let idLab = medic ? medic.idUsuario : 0;
      let exameneslab = new ExamenesLabs(idLab,parseInt(idExamen),"Saber",parseInt(precio),"PENDIENTE")
      this.servicioExamenes.postNewExamenLab(exameneslab).
      subscribe(data=>{
        console.log(JSON.stringify(data))
      })
    }
  }
}

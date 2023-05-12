import { Component, OnInit } from '@angular/core';
import { ExamenesLabs } from 'src/app/Modelo/ExamenesLabs';
import { ServiceExamenesService } from 'src/app/Service/service-examenes.service'

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponentLab implements OnInit {
  examenes!: ExamenesLabs[];
  examenMod!: ExamenesLabs;

  constructor(private service: ServiceExamenesService) { }
  ngOnInit(): void {
    this.service.getExamenesLabs().subscribe(data => {
      this.examenes = data;
    })
  }

  traerExameneslab() {
    this.service.getExamenesLabs().subscribe(data => {
      this.examenes = data;
    })
  }

  aceptarExm(examen:ExamenesLabs){
    this.examenMod = new ExamenesLabs(examen.idLaboratorio,examen.idExamen, examen.nombreExamen,
      examen.precio,"ACEPTADA");
    this.service.putExamenesLabs(this.examenMod)
      .subscribe(data=>{
        this.examenMod=data;
        console.log(this.examenMod.idLaboratorio);
        console.log(this.examenMod.idExamen);
        console.log(this.examenMod.estado);
      },error=>{
        console.log(error);        
      });
      this.traerExameneslab();
  }

  recharzarExm(examen:ExamenesLabs){
    this.examenMod = new ExamenesLabs(examen.idLaboratorio,examen.idExamen, examen.nombreExamen,
      examen.precio,"RECHAZADA");
    this.service.putExamenesLabs(this.examenMod)
      .subscribe(data=>{
        this.examenMod=data;
        console.log(this.examenMod.idLaboratorio);
        console.log(this.examenMod.idExamen);
        console.log(this.examenMod.estado);
      }, error=>{
        console.log(error)
      });
      this.traerExameneslab();
  }

  

}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from '../../Service/service.EspecialidadesMedicas'
import { EspecialidadesMedicas } from 'src/app/Modelo/EspecialidadesMedicas';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit {

  especialidades: EspecialidadesMedicas[] | undefined;
  espModificada!: EspecialidadesMedicas;
  constructor(private service: ServiceService, private router: Router) { }

  ngOnInit(): void {
    this.service.getEspecialidades()
      .subscribe(data => {
        this.especialidades = data;
      })
  }

  aceptarEsp(especialidad: EspecialidadesMedicas) {
    this.espModificada = new EspecialidadesMedicas(especialidad.idEspecialidad, especialidad.idMedico,
      especialidad.nombreEspecialidad, especialidad.nombreMedico, especialidad.precio, "ACEPTADA");
    this.service.putEspecialidades(this.espModificada)
      .subscribe(data => {
        this.espModificada = data;
        console.log(this.espModificada.idEspecialidad);
        console.log(this.espModificada.idMedico);
        console.log(this.espModificada.estado);
        this.traerEsp();
      }, error => {
        console.log(error);
      });
  }

  traerEsp() {
    this.service.getEspecialidades()
      .subscribe(data => {
        this.especialidades = data;
      })
  }

  recharzarEsp(especialidad: EspecialidadesMedicas) {
    this.espModificada = new EspecialidadesMedicas(especialidad.idEspecialidad, especialidad.idMedico,
      especialidad.nombreEspecialidad, especialidad.nombreMedico, especialidad.precio, "RECHAZADA");
    this.service.putEspecialidades(this.espModificada)
      .subscribe(data => {
        this.espModificada = data;
        console.log(this.espModificada.idEspecialidad);
        console.log(this.espModificada.idMedico);
        console.log(this.espModificada.estado);
        this.traerEsp();
      }, error => {
        console.log(error);
      });
   
  }

}

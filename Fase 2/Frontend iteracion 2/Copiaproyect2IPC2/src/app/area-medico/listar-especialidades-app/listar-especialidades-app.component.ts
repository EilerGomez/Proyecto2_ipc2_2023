import { Component, OnInit } from '@angular/core';
import { Especialidades } from 'src/app/Modelo/Especialidades';
import { EspecialidadesMedicas } from 'src/app/Modelo/EspecialidadesMedicas';
import { Medicos } from 'src/app/Modelo/Medicos';
import { ServiceService } from 'src/app/Service/service.EspecialidadesMedicas';

@Component({
  selector: 'app-listar-especialidades-app',
  templateUrl: './listar-especialidades-app.component.html',
  styleUrls: ['./listar-especialidades-app.component.css']
})
export class ListarEspecialidadesAppComponent implements OnInit {
  idMedico!: number
  idEspecialidad!: number
  precio!: number
  especialidades !: Especialidades[]
  nuevaEsp!: EspecialidadesMedicas

  constructor(private service: ServiceService) { }

  ngOnInit(): void {
    this.service.getEspecialidadesDeLaAPP()
      .subscribe(data => {
        this.especialidades = data;
      }, error => {
        console.log(error)
      }
      );
  }
  solicitarEspecialidad(idesp: string, precio: string) {
    this.precio = parseFloat(precio)
    this.idEspecialidad = parseInt(idesp)
    let stringUser = localStorage.getItem('usuario');
    let medic: Medicos = stringUser ? JSON.parse(stringUser) : null;
    this.idMedico = medic ? medic.idUsuario : 0;
    this.nuevaEsp = new EspecialidadesMedicas(this.idEspecialidad, this.idMedico, "name esp", "name medic", this.precio, "PENDIENTE")


    this.service.postEspecialidadMedico(this.nuevaEsp)
      .subscribe(data => {
        this.nuevaEsp = data;
        console.log(this.nuevaEsp.idEspecialidad);
        console.log(this.nuevaEsp.idMedico);
        console.log(this.nuevaEsp.precio);
        console.log(this.nuevaEsp.estado);
      });

  }


}

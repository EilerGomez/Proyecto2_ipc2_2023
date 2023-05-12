import { Component, OnInit } from '@angular/core';
import { EspecialidadesMedicas } from 'src/app/Modelo/EspecialidadesMedicas';
import { Medicos } from 'src/app/Modelo/Medicos';
import { ServiceService } from 'src/app/Service/service.EspecialidadesMedicas';

@Component({
  selector: 'app-listar-esp-medico',
  templateUrl: './listar-esp-medico.component.html',
  styleUrls: ['./listar-esp-medico.component.css']
})
export class ListarEspMedicoComponent implements OnInit {
  idMedico!: number
  constructor(private service: ServiceService) { }
  especialidad!: EspecialidadesMedicas
  especialidades!: EspecialidadesMedicas[]
  mostrarEditar: boolean = false
  precioEsp!: number;
  newEsp!: EspecialidadesMedicas;

  ngOnInit(): void {
    let stringUser = localStorage.getItem('usuario');
    let medic: Medicos = stringUser ? JSON.parse(stringUser) : null;
    this.idMedico = medic ? medic.idUsuario : 0;
    this.service.getEspecialidadesMedico(this.idMedico)
      .subscribe(data => {
        this.especialidades = data;
      }, error => {
        console.log(error)
      }
      );
  }
  actualizarEsp(especialidad1: EspecialidadesMedicas, precio: string) {
    this.precioEsp = parseFloat(precio);
    this.newEsp = new EspecialidadesMedicas(especialidad1.idEspecialidad, especialidad1.idMedico, especialidad1.nombreEspecialidad,
      especialidad1.nombreMedico, this.precioEsp, especialidad1.estado);

    this.service.putEspecialidades(this.newEsp)
      .subscribe(data => {
        this.newEsp = data;
        console.log(this.newEsp.idEspecialidad)
        console.log(this.newEsp.nombreMedico)
        console.log(this.newEsp.precio)
      }, error => { console.log(error) });
      this.ngOnInit();
  }
  Editar(especialidad1: EspecialidadesMedicas) {
    this.mostrarEditar = true;
    this.especialidad = new EspecialidadesMedicas(especialidad1.idEspecialidad, especialidad1.idMedico, especialidad1.nombreEspecialidad,
      especialidad1.nombreMedico, especialidad1.precio, especialidad1.estado);
  }

  traerEspecialidades() {
    let stringUser = localStorage.getItem('usuario');
    let medic: Medicos = stringUser ? JSON.parse(stringUser) : null;
    this.idMedico = medic ? medic.idUsuario : 0;
    this.service.getEspecialidadesMedico(this.idMedico)
      .subscribe(data => {
        this.especialidades = data;
      }, error => {
        console.log(error)
      }
      );
  }

  Reenviar(especialidad1:EspecialidadesMedicas){
    this.newEsp = new EspecialidadesMedicas(especialidad1.idEspecialidad, especialidad1.idMedico, especialidad1.nombreEspecialidad,
      especialidad1.nombreMedico, especialidad1.precio,"PENDIENTE");

    this.service.putEspecialidades(this.newEsp)
      .subscribe(data => {
        this.newEsp = data;
        console.log(this.newEsp.idEspecialidad)
        console.log(this.newEsp.nombreMedico)
        console.log(this.newEsp.precio)
      }, error => { console.log(error) });
      this.ngOnInit();
  }

}

import { Component } from '@angular/core';
import { Consultas } from 'src/app/Modelo/Consultas';
import { Examenes } from 'src/app/Modelo/Examenes';
import { Medicos } from 'src/app/Modelo/Medicos';
import { ServiceHistorialMedicoService } from 'src/app/Service/service-historial-medico.service';

@Component({
  selector: 'app-historial-medico',
  templateUrl: './historial-medico.component.html',
  styleUrls: ['./historial-medico.component.css']
})
export class HistorialMedicoComponent {
  constructor(private service: ServiceHistorialMedicoService) { }

  consultas: Consultas[]=[]
  examenes: Examenes[]=[]
  mostrarTablas: boolean = false

  verExamenes(idPaciente: string) {
    var IdPaciente1 = parseInt(idPaciente);
    this.service.getExamenesPaciente(IdPaciente1)
      .subscribe(data => {
        this.examenes = data;
        this.verConsultas(idPaciente)
      }, error => {
        console.log(error)
        this.examenes = [];
        this.verConsultas(idPaciente)
      });
    this.mostrarTablas = true
  }

  verConsultas(idPaciente: string) {
    let stringUser = localStorage.getItem('usuario');
    let medic: Medicos = stringUser ? JSON.parse(stringUser) : null;
    let idMedico = medic ? medic.idUsuario : 0;
    var IdPaciente1 = parseInt(idPaciente);
    this.service.getConsultasPaciente(IdPaciente1, idMedico)
      .subscribe(data => {
        this.consultas = data;
      }, error => {
        console.log(error)
        this.consultas = [];
      });
      this.mostrarTablas=true
  }

}

import { Component } from '@angular/core';
import { HorariosMedicos } from 'src/app/Modelo/HorariosMedicos';
import { Medicos } from 'src/app/Modelo/Medicos';
import { ServiceHorariosMedicosService } from 'src/app/Service/service-horarios-medicos.service';

@Component({
  selector: 'app-nuevo-horario',
  templateUrl: './nuevo-horario.component.html',
  styleUrls: ['./nuevo-horario.component.css']
})
export class NuevoHorarioComponent {
  mostrarExito: boolean = false;
  mostrarError: boolean = false;
  nuevoHorario!: HorariosMedicos
  constructor(private service: ServiceHorariosMedicosService) { }

  guardarHorarrio(horaEntrada: string, horaSalida: string) {

    let stringUser = localStorage.getItem('usuario');
    let medic: Medicos = stringUser ? JSON.parse(stringUser) : null;
    let idMedico = medic ? medic.idUsuario : 0;
    this.nuevoHorario = new HorariosMedicos(idMedico,idMedico, horaEntrada+":00", horaSalida+":00");
   
    console.log(JSON.stringify(this.nuevoHorario));
    this.service.postNewHorary(this.nuevoHorario)
      .subscribe(data => {
        this.nuevoHorario = data;
        console.log(this.nuevoHorario.horaEntrada)
        console.log(this.nuevoHorario.horaSalida)
        this.mostrarExito = true;
        this.mostrarError=false;
      }, error => {
        console.log(error)
        this.mostrarError = true
        this.mostrarExito=false
      });
  }
}

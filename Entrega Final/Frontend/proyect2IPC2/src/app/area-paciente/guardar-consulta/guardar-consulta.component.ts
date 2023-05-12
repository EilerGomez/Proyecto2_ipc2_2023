import { Component, Input, OnInit } from '@angular/core';
import { HorariosMedicos } from 'src/app/Modelo/HorariosMedicos';
import { Medicos } from 'src/app/Modelo/Medicos';
import { ServiceHorariosMedicosService } from 'src/app/Service/service-horarios-medicos.service'
import { ServiceConsultasService } from 'src/app/Service/service-consultas.service'
import { Consultas } from 'src/app/Modelo/Consultas';
import { Pacientes } from 'src/app/Modelo/Paciente';
import { ServiceService } from 'src/app/Service/service.EspecialidadesMedicas'
import { EspecialidadesMedicas } from 'src/app/Modelo/EspecialidadesMedicas';
import { ServiceCobroAppService } from 'src/app/Service/service-cobro-app.service';
import { cobroApp } from 'src/app/Modelo/cobroApp';

@Component({
  selector: 'app-guardar-consulta',
  templateUrl: './guardar-consulta.component.html',
  styleUrls: ['./guardar-consulta.component.css']
})
export class GuardarConsultaComponent implements OnInit {
  constructor(private service: ServiceHorariosMedicosService, private servicioCosn: ServiceConsultasService,
    private servicioEsp: ServiceService, private serviceCobroApp: ServiceCobroAppService) { }


  mostrarAlert: boolean = false
  mostrarAlertaDeNoguardado: boolean = false
  mostrarErrorDeDinero:boolean=false
  mostrarInfo:boolean=false
  mostrarExitoConsulat:boolean=false
  //esta variable es para ver la hora inicial
  splitHInicial!: string[]
  splitHFinal!: string[]
  horas: String[] = []
  @Input() medico!: Medicos;
  @Input() idEspecialidad!: number
  horarioEdit!: HorariosMedicos
  horarios!: HorariosMedicos[]
  fecha: string = "null"


  ngOnInit(): void {
    this.service.getHorariosDeMedico(this.medico.idUsuario)
      .subscribe(data => {
        this.horarios = data;
      }, error => { console.log(error) })
  }
  medicos!: Medicos[]
  mostrarHorario: boolean = false;
  consultaAGuardar!:Consultas


  verHorarios(dia: string, horario: HorariosMedicos) {
    this.horas = []
    this.fecha = dia
    this.splitHInicial = horario.horaEntrada.split(":");
    this.splitHFinal = horario.horaSalida.split(":");
    let horaEntrada: any = parseInt(this.splitHInicial[0]);
    let horaSalida: any = parseInt(this.splitHFinal[0])
    console.log(horaEntrada)
    console.log(horaSalida)
    for (let i = horaEntrada; i < horaSalida; i++) {
      this.horas.push(i + ":" + this.splitHInicial[1] + ":" + this.splitHInicial[2])
      console.log(this.fecha)
    }
    if (dia == null || dia == "") {
      this.mostrarHorario = false
      this.mostrarAlert = true
    } else {
      this.mostrarAlert = false
      this.mostrarHorario = true
    }

  }
  mostrarHoras(horarrio: HorariosMedicos) {
    this.horarioEdit = horarrio;
    console.log(horarrio.horaEntrada)
  }
  verHora(idHorario: string, hora: String) {
    console.log(this.fecha + " " + hora + ":00")
  }

  guardarConsulta(hora: String) {

    let consultas: Consultas[]
    console.log(this.fecha + " " + hora)
    this.servicioCosn.getTodasConsultas(this.medico.idUsuario)
      .subscribe(data => {
        consultas = data;
        for (let index = 0; index < consultas.length; index++) {
          if (consultas[index].fechaAgendada == this.fecha + " " + hora) {
            console.log("ya hay agendacion")
            this.mostrarAlertaDeNoguardado = true
            index = consultas.length
          } else {
            this.mostrarAlertaDeNoguardado = false
          }
        }
        if (this.mostrarAlertaDeNoguardado == false) {
          console.log("Logica de guardar consulta")
          this.generarNuevaConsulta(this.fecha + " " + hora)
        }
      }, err => { console.log(err) });

  }

  generarNuevaConsulta(fechaAgendada: String) {
    this.servicioEsp.getEspecialidadesMedico(this.medico.idUsuario)
      .subscribe(data => {
        let especialidades!: EspecialidadesMedicas[]
        especialidades = data
        for (let index = 0; index < especialidades.length; index++) {
          if (especialidades[index].idEspecialidad == this.idEspecialidad) {
            let stringUser = localStorage.getItem('usuario');
            let medic: Pacientes = stringUser ? JSON.parse(stringUser) : null;
            let idPaciente = medic ? medic.idUsuario : 0;
            let consulta: Consultas = new Consultas();
            consulta.idConsulta = 2
            consulta.idPaciente = idPaciente;
            consulta.idMedico = this.medico.idUsuario
            consulta.idEspecialidad = this.idEspecialidad

            let cobro!: cobroApp
            this.serviceCobroApp.getUltimoRegistro().subscribe(data => {
              cobro = data
              consulta.porcentajeCobroApp = cobro.porcentaje
              consulta.fechaCreacion = "2023-04-05"
              consulta.fechaAgendada = fechaAgendada
              consulta.precio = especialidades[index].precio
              consulta.informe = "ninguno"
              consulta.estado = "AGENDADA"
              console.log(JSON.stringify(consulta))
              this.consultaAGuardar=consulta
              this.mostrarInfo=true
            })
          }

        }
      });


  }

  GuardarConsultaEnDB(){
    this.servicioCosn.postNewConsulta(this.consultaAGuardar)
    .subscribe(data=>{
      console.log(JSON.stringify(data))
      this.mostrarExitoConsulat=true
      this.mostrarInfo=false
    }, error=>{
      console.log(error)
      this.mostrarErrorDeDinero=true
    })
  }

}

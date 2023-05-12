import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { ExamenesLabs } from 'src/app/Modelo/ExamenesLabs';
import { ExamenesSolicitud } from 'src/app/Modelo/ExamenesSolicitud';
import { Laboratorios } from 'src/app/Modelo/Laboratorio';
import { Pacientes } from 'src/app/Modelo/Paciente';
import { Solicitudes } from 'src/app/Modelo/Solicitudes';
import { cobroApp } from 'src/app/Modelo/cobroApp';
import { ServiceCobroAppService } from 'src/app/Service/service-cobro-app.service';
import { ServiceExamenesSolicitudService } from 'src/app/Service/service-examenes-solicitud.service';
import { ServiceExamenesService } from 'src/app/Service/service-examenes.service';
import { ServiceSolicitudesService } from 'src/app/Service/service-solicitudes.service';

@Component({
  selector: 'app-agregar-examenes-a-solicitud',
  templateUrl: './agregar-examenes-a-solicitud.component.html',
  styleUrls: ['./agregar-examenes-a-solicitud.component.css']
})
export class AgregarExamenesASolicitudComponent implements OnInit {
  constructor(private serviceExmLab: ServiceExamenesService, private serviceCobroApp: ServiceCobroAppService, private servicioSolicitudes
    : ServiceSolicitudesService, private servicioExamenesSolicitud: ServiceExamenesSolicitudService) { }
  @Input() laboratorio!: Laboratorios
  @Output() mostrarAgregarExamen: EventEmitter<boolean> = new EventEmitter();
  examenesSolicitud!: ExamenesSolicitud[]
  exameneslab!: ExamenesLabs[]
  idNuevaSolicitud!: number
  idPaciente!: number
  mostrarAletra:boolean=false

  ngOnInit(): void {
    this.serviceExmLab.getExamenesAceptadoslab(this.laboratorio.idUsuario)
      .subscribe(data => {
        this.exameneslab = data;
        let stringUser = localStorage.getItem('usuario');
        let medic: Pacientes = stringUser ? JSON.parse(stringUser) : null;
        let idPaciente = medic ? medic.idUsuario : 0;
        let solicitudAAgregar: Solicitudes = new Solicitudes()
        solicitudAAgregar.idSolicitud = 2
        solicitudAAgregar.idPaciente = idPaciente; this.idPaciente = idPaciente
        solicitudAAgregar.idLaboratorio = this.laboratorio.idUsuario
        solicitudAAgregar.fechaSolicitada = "2023-01-01"
        solicitudAAgregar.estado = "PENDIENTE"
        let cobro!: cobroApp
        this.serviceCobroApp.getUltimoRegistro()
          .subscribe(data => {
            cobro = data;
            solicitudAAgregar.porcentajeCobroApp = cobro.porcentaje
            solicitudAAgregar.fechaFinalizada = ""
            console.log(JSON.stringify(solicitudAAgregar))
            this.servicioSolicitudes.postNuevaSolicitud(solicitudAAgregar)
              .subscribe(data => {
                console.log(JSON.stringify(data))
                this.servicioSolicitudes.getUltimaSolicitudHecha(idPaciente)
                  .subscribe(data => {
                    this.idNuevaSolicitud = data
                  })
              })
          })
      }, error => {
        console.log(error)
      })
  }

  Volver() {
    this.mostrarAgregarExamen.emit(false)
  }

  traerExamenesDeSolicitud() {
    this.servicioExamenesSolicitud.getExamenesSolicitud(this.idNuevaSolicitud)
      .subscribe(data => {
        this.examenesSolicitud = data;
      }, eror => {
        console.log(eror)
      })
  }

  agregarExamenesSolicitud(examen: ExamenesLabs) {
    let examenSolicitud: ExamenesSolicitud = new ExamenesSolicitud();
    examenSolicitud.idExamen = examen.idExamen;
    examenSolicitud.idSolicitud = this.idNuevaSolicitud;
    examenSolicitud.nombreExamen = "sepa"
    examenSolicitud.precio = examen.precio
    console.log(JSON.stringify(examenSolicitud))
    let cobro!: cobroApp
    this.serviceCobroApp.getUltimoRegistro()
      .subscribe(data => {
        cobro = data;

        this.servicioExamenesSolicitud.postNuevoExamenSolicitud(examenSolicitud, cobro.porcentaje, this.laboratorio.idUsuario, this.idPaciente)
          .subscribe(data => {
            console.log(JSON.stringify(data))
            this.traerExamenesDeSolicitud()
          }, error => { console.log(error) 
          this.mostrarAletra=true
          })
      })
  }


}


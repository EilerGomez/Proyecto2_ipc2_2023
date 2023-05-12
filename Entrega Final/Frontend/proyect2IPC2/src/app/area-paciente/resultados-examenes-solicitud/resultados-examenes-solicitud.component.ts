import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { ExamenesSolicitud } from 'src/app/Modelo/ExamenesSolicitud';
import { Solicitudes } from 'src/app/Modelo/Solicitudes';
import { ServiceExamenesSolicitudService } from 'src/app/Service/service-examenes-solicitud.service';

@Component({
  selector: 'app-resultados-examenes-solicitud',
  templateUrl: './resultados-examenes-solicitud.component.html',
  styleUrls: ['./resultados-examenes-solicitud.component.css']
})
export class ResultadosExamenesSolicitudComponent implements OnInit {
  constructor(private service: ServiceExamenesSolicitudService) { }

  @Input() solicitud!: Solicitudes
  examenesSolicitud!: ExamenesSolicitud[]
  @Output() mostrar = new EventEmitter<boolean>();
  ngOnInit(): void {
    this.service.getExamenesSolicitud(this.solicitud.idSolicitud)
      .subscribe(data => {
        this.examenesSolicitud = data
      })
  }

  volver() {
    this.mostrar.emit(false);
  }
}

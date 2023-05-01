import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Consultas } from 'src/app/Modelo/Consultas';
import { Examenes } from 'src/app/Modelo/Examenes';
import { ExamenesConsultas } from 'src/app/Modelo/ExamenesConsultas';
import { ServiceExamenesConsultasService } from 'src/app/Service/service-examenes-consultas.service';
import { ServiceExamenesService } from 'src/app/Service/service-examenes.service';

@Component({
  selector: 'app-agregar-examen-a-consultas',
  templateUrl: './agregar-examen-a-consultas.component.html',
  styleUrls: ['./agregar-examen-a-consultas.component.css']
})
export class AgregarExamenAConsultasComponent implements OnInit {
  constructor(private service: ServiceExamenesService, private serviceEC: ServiceExamenesConsultasService) { }
  examenesApp: Examenes[] = []
  @Input() consulta!: Consultas
  idExamen!: number
  mostrarAgregarExamenes: boolean = false

  examenesConsulta!: ExamenesConsultas[]
  ngOnInit(): void {
    this.service.getExamenesDeLaApp()
      .subscribe(data => {
        this.examenesApp = data;
        this.listarExamenes();
        console.log(JSON.stringify(this.examenesApp))
      }, error => { console.log(error) })


  }

  listarExamenes() {
    this.serviceEC.getExamenesConsultas(this.consulta.idConsulta)
      .subscribe(data => {
        this.examenesConsulta = data;
      }, error => {
        console.log(error)
      })
  }

  agregarExamen(idExamenn: string) {
    this.idExamen = parseInt(idExamenn);
    this.serviceEC.postExamenesConsulta(this.consulta.idConsulta, this.idExamen)
      .subscribe(data => {
        console.log(data)
        this.listarExamenes();
      }, error => {
        console.log(error)
      });
  }

  @Output() noMostrarAgregarExamenes = new EventEmitter<boolean>();
  Guardar() {
    this.noMostrarAgregarExamenes.emit(this.mostrarAgregarExamenes);
  }
}

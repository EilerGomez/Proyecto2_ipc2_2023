import { Component, OnInit } from '@angular/core';
import { Consultas } from 'src/app/Modelo/Consultas';
import { Medicos } from 'src/app/Modelo/Medicos';
import { ServiceConsultasService } from 'src/app/Service/service-consultas.service';

@Component({
  selector: 'app-consultas-agendadas',
  templateUrl: './consultas-agendadas.component.html',
  styleUrls: ['./consultas-agendadas.component.css']
})
export class ConsultasAgendadasComponent implements OnInit {
  constructor(private service: ServiceConsultasService) { }
  mostrarAgregarExamenes=false;
  dia: String = "0";
  consultas!: Consultas[]
  consultaAEditar!:Consultas

  ngOnInit(): void {
    let stringUser = localStorage.getItem('usuario');
    let medic: Medicos = stringUser ? JSON.parse(stringUser) : null;
    let idMedico = medic ? medic.idUsuario : 0;
    this.service.getConsultasAgendadasPorDia(idMedico, this.dia)
      .subscribe(data => {
        this.consultas = data;
        this.dia=this.consultas[0].fechaCreacion
      }, error => {
        console.log(error)
        var cons!: Consultas[]
        this.consultas = cons;
      })
  }
  SeleccionarDia(fecha: String) {
    console.log(fecha)
    this.dia = fecha;
    this.ngOnInit();
  }

  EnviarAEditar(consulta:Consultas){
    this.consultaAEditar=consulta;
    this.mostrarAgregarExamenes=true
  }
  NoMostrarAggExms(noMostrar:boolean){
    this.mostrarAgregarExamenes=noMostrar;
    this.SeleccionarDia(this.dia);
  }

}

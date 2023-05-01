import { Component, OnInit } from '@angular/core';
import { Consultas } from 'src/app/Modelo/Consultas';
import { Medicos } from 'src/app/Modelo/Medicos';
import { ServiceConsultasService } from 'src/app/Service/service-consultas.service';

@Component({
  selector: 'app-consultas-pendientes',
  templateUrl: './consultas-pendientes.component.html',
  styleUrls: ['./consultas-pendientes.component.css']
})
export class ConsultasPendientesComponent implements OnInit{

  constructor(private service:ServiceConsultasService){}
  MostrarFinalizarConsulta:boolean=false
  consultaEditar!:Consultas;
  consultas!:Consultas[]
  dia:String="0";
  ngOnInit(): void {
    let stringUser = localStorage.getItem('usuario');
    let medic: Medicos = stringUser ? JSON.parse(stringUser) : null;
    let idMedico = medic ? medic.idUsuario : 0;
    this.service.getConsultasPorDia(idMedico,this.dia)
    .subscribe(data=>{
      this.consultas=data;
    }, error=>{console.log(error)
      var cons!: Consultas[]
      this.consultas=cons;
    })
    
  }
  SeleccionarDia(fecha:String){
    console.log(fecha)
    this.dia=fecha;
    this.ngOnInit();
  }

  EnviarAEditar(consulta:Consultas){
    this.consultaEditar=consulta;
    this.MostrarFinalizarConsulta=true;
  }

  NoMostrarFinalizar(mostrarFinalizarConsulta:boolean){
    this.MostrarFinalizarConsulta=mostrarFinalizarConsulta;
    
  }
  ActualizarTabla(){
    this.SeleccionarDia(this.dia);
  }

}

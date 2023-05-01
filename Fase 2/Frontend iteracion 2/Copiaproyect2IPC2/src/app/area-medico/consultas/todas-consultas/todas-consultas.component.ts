import { Component, OnInit } from '@angular/core';
import { Consultas } from 'src/app/Modelo/Consultas';
import { Medicos } from 'src/app/Modelo/Medicos';
import { ServiceConsultasService } from 'src/app/Service/service-consultas.service';

@Component({
  selector: 'app-todas-consultas',
  templateUrl: './todas-consultas.component.html',
  styleUrls: ['./todas-consultas.component.css']
})
export class TodasConsultasComponent implements OnInit{
  constructor(private service:ServiceConsultasService){}

  consultas!:Consultas[]
  ngOnInit(): void {
    let stringUser = localStorage.getItem('usuario');
    let medic: Medicos = stringUser ? JSON.parse(stringUser) : null;
    let idMedico = medic ? medic.idUsuario : 0;
    this.service.getTodasConsultas(idMedico)
    .subscribe(data=>{
      this.consultas=data;
    }, error=>{
      console.log(error)
    })
  }

}

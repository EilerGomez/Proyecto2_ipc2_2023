import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HorariosMedicos } from 'src/app/Modelo/HorariosMedicos';
import { Medicos } from 'src/app/Modelo/Medicos';
import { ServiceHorariosMedicosService } from 'src/app/Service/service-horarios-medicos.service';

@Component({
  selector: 'app-listar-horarios',
  templateUrl: './listar-horarios.component.html',
  styleUrls: ['./listar-horarios.component.css']
})
export class ListarHorariosComponent implements OnInit{
  mostrarEditar:boolean=false
  horarios!:HorariosMedicos[]
  horarioEdit!:HorariosMedicos
  mostrarExito:boolean=false
  mostrarError:boolean=false

  constructor(private service:ServiceHorariosMedicosService, private router: Router){}
  ngOnInit(): void {
    let stringUser = localStorage.getItem('usuario');
    let medic: Medicos = stringUser ? JSON.parse(stringUser) : null;
    let idMedico = medic ? medic.idUsuario : 0;
    this.service.getHorariosDeMedico(idMedico)
    .subscribe(data=>{
      this.horarios=data;
    },error=>{console.log(error)});
  }

  actualizarHorario(horaEntarda:String, horaSalida:String){
    var HE=horaEntarda;
    var HS=horaSalida;
    var split1 = horaEntarda.split(":")
    var split2 = horaSalida.split(":")
      if(split1.length==2){
        HE=horaEntarda+":00";
      }
      if(split2.length==2){
        HS = horaSalida+":00";
      }
      console.log(horaEntarda)
      console.log(horaSalida)
      console.log(HE)
      console.log(HS)
      let newHorario = new HorariosMedicos(this.horarioEdit.idHorario,this.horarioEdit.idMedico,HE,HS);  
      console.log(JSON.stringify(newHorario));    
      this.service.putHorario(newHorario)
      .subscribe(data=>{
        console.log(data)
        this.horarios = this.horarios.map(s => s.idHorario=== data.idHorario ? data : s);        
        this.mostrarError=false
        this.mostrarExito=true
        this.horarioEdit=data;
      }, error=>{
        console.log(error)
        this.mostrarError=true
        this.mostrarExito=false
      })
      
  }
  EditarHorario(horario:HorariosMedicos){
    this.horarioEdit= new HorariosMedicos(horario.idHorario, horario.idMedico,horario.horaEntrada, horario.horaSalida);
    this.mostrarEditar=true
  }
  



}

import { Component } from '@angular/core';
import { Administrador } from '../Modelo/Administrador';
import { Medicos } from '../Modelo/Medicos';
import { Pacientes } from '../Modelo/Paciente';
import { Laboratorios } from '../Modelo/Laboratorio';

@Component({
  selector: 'app-area-medico',
  templateUrl: './area-medico.component.html',
  styleUrls: ['./area-medico.component.css']
})
export class AreaMedicoComponent {

  email:String = "";
  phone:String = "";
  getPhone(){
    let stringUser = localStorage.getItem('usuario');
    let area = localStorage.getItem('area');    
    if (area == "1") {
      let admin: Administrador = stringUser ? JSON.parse(stringUser) : null;
      this.phone= admin ? admin.userName : '';
    }else if(area=="2"){
      let medic: Medicos = stringUser ? JSON.parse(stringUser) : null;
      this.phone= medic ? medic.telefono : '';
    }else if(area=="3"){
      let pacient: Pacientes = stringUser ? JSON.parse(stringUser) : null;
      this.phone= pacient ? pacient.telefono : '';
    }else if(area=="4"){
      let lab: Laboratorios = stringUser ? JSON.parse(stringUser) : null;
      this.phone= lab ? lab.telefono : '';
    }
    return this.phone;
  }
  getEmail(){
    let stringUser = localStorage.getItem('usuario');
    let area = localStorage.getItem('area');    
    if (area == "1") {
      let admin: Administrador = stringUser ? JSON.parse(stringUser) : null;
      this.email= admin ? admin.email : '';
    }else if(area=="2"){
      let medic: Medicos = stringUser ? JSON.parse(stringUser) : null;
      this.email= medic ? medic.email : '';
    }else if(area=="3"){
      let pacient: Pacientes = stringUser ? JSON.parse(stringUser) : null;
      this.email= pacient ? pacient.email : '';
    }else if(area=="4"){
      let lab: Laboratorios = stringUser ? JSON.parse(stringUser) : null;
      this.email= lab ? lab.email : '';
    }
    return this.email;
  }
}

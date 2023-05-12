import { Component, ViewChild } from '@angular/core';
import {LoginComponent} from '../../app/login/login.component'
import { Administrador } from '../Modelo/Administrador';
import { Router } from '@angular/router';
import { Medicos } from '../Modelo/Medicos';
import { Pacientes } from '../Modelo/Paciente';
import { Laboratorios } from '../Modelo/Laboratorio';

@Component({
  selector: 'app-area-administrador',
  templateUrl: './area-administrador.component.html',
  styleUrls: ['./area-administrador.component.css']
})
export class AreaAdministradorComponent {
  @ViewChild(LoginComponent) child: any;
  email:String = "";
  phone:String = "";

  administrador!:Administrador;
  mostrarAlert:boolean=true;

  constructor(private router:Router){}

  ngOnInit(){
  }

  ngAfterViewInit() {
    this.administrador = this.child.administrador;
    this.mostrarAlert=this.child.mostrarAlert;
  }
  decirHola(){
    console.log("Hola mundo")
    this.router.navigate(["listar"])
  }
  cerrarSesion(){
    this.router.navigate(["login"]);
  }
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

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Administrador } from '../Modelo/Administrador';
import { Medicos } from '../Modelo/Medicos';
import { Pacientes } from '../Modelo/Paciente';
import { Laboratorios } from '../Modelo/Laboratorio';
import { LoginService } from '../Service/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  constructor(private router: Router, private service: LoginService) { }


  email:String = "";
  userLoggedIn() {
    return localStorage.getItem('usuario') ? true : false;
  }

  logOut() {
    localStorage.removeItem('usuario');
    localStorage.removeItem('area');
    this.router.navigate(['/homepage']);
    this.service.putCerrarConexionDB("a").subscribe(data=>{
      console.log(data)
    }, error=>{console.log(error)});
  }

  getUserEmail() {
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

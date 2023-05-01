import { Component } from '@angular/core';
import { Login } from '../Modelo/Login';
import { NgForm } from '@angular/forms';
import {LoginService} from '../Service/login.service'
import { Router } from '@angular/router';
import {Administrador} from '../Modelo/Administrador'
import { Medicos } from '../Modelo/Medicos';
import { Pacientes } from '../Modelo/Paciente';
import { Laboratorios } from '../Modelo/Laboratorio';
import {AppComponent} from '../app.component'
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  mostrarAlert:boolean = false;
  login!: Login;
  administrador!:Administrador;
  medico!: Medicos;
  paciente!: Pacientes;
  laboratorio!: Laboratorios;
  componentePrincipal!:AppComponent
 
  constructor(private service:LoginService, private router:Router ){
   
  }
    impimirEnConsola(username:String, password:string, area:String){
      
    }    

    traerUsuario(username:string, password:string, area:string):void{
      this.login=new Login(username,password,area);
      console.log("Hola tu eres:" +  this.login.getUserName() + ", password: " + this.login.getPassword() + ", en el area: " + this.login.getArea());

      this.service.getLogin(this.login).subscribe((resp:any)=>{
        console.log(resp);
        localStorage.setItem("usuario",JSON.stringify(resp));
        localStorage.setItem("area",area);
        this.router.navigate(["homepage"])
      },err=>{
          this.mostrarAlert=true;
        }
      );
    }
    
    redireccionar(resp:any, area:String):void{
      if(resp==null){
        this.mostrarAlert=true;
      }
      if(area=="1"){
        this.administrador=resp;
        console.log(this.administrador.email)
        this.router.navigate(["areaAdministrador"])        
      }else if(area=="2"){
        this.medico=resp;
        console.log(this.medico.email)
      }else if(area=="3"){
        this.paciente=resp;
      }else if(area=="4"){
       this.laboratorio=resp;
      }
    }
}

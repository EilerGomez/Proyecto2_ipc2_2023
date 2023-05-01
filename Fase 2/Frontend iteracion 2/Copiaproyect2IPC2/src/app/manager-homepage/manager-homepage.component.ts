import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manager-homepage',
  template: ``,
  styles: [``]
})
export class ManagerHomepageComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
    let stringUser = localStorage.getItem("usuario");
    let user = stringUser ? JSON.parse(stringUser) : null;
    let area = localStorage.getItem("area");

    if (user && area) {
      if (area == "1") {
        this.router.navigate(["areaAdministrador"])
      } else if (area == "2") {
        this.router.navigate(["areaMedico"])
      } else if (area == "3") {
        // this.router.navigate(["areaPaciente"])
      } else if (area == "4") {
        // this.router.navigate(["areaLaboratorio"])
      }
    }
    else {
      this.router.navigate(["login"])
    }
  }

  // redireccionar(resp: any, area: String): void {
  //   if (resp == null) {
  //     this.mostrarAlert = true;
  //   }
  //   if (area == "1") {
  //     this.administrador = resp;
  //     console.log(this.administrador.email)
  //     this.router.navigate(["areaAdministrador"])
  //   } else if (area == "2") {
  //     this.medico = resp;
  //     console.log(this.medico.email)
  //   } else if (area == "3") {
  //     this.paciente = resp;
  //   } else if (area == "4") {
  //     this.laboratorio = resp;
  //   }
  // }

}

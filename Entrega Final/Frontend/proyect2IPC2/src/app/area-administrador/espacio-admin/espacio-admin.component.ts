import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-espacio-admin',
  templateUrl: './espacio-admin.component.html',
  styleUrls: ['./espacio-admin.component.css']
})
export class EspacioAdminComponent implements OnInit {

  constructor(private router:Router){

  }
  ngOnInit(): void {
    this.router.navigate(["listar"])
  }
  decirHola(){
    this.router.navigate(["listar"])
  }

}

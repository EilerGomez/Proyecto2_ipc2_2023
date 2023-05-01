import { Component, OnInit } from '@angular/core';
import { cobroApp } from '../Modelo/cobroApp';
import { ServiceCobroAppService } from '../Service/service-cobro-app.service';

@Component({
  selector: 'app-historial-cobro',
  templateUrl: './historial-cobro.component.html',
  styleUrls: ['./historial-cobro.component.css']
})
export class HistorialCobroComponent implements OnInit {

  cobroApp!: cobroApp[]
  constructor(private service:ServiceCobroAppService){}

  ngOnInit(): void {
    this.service.getHistorial().subscribe(data=>{
      this.cobroApp=data;
    })
  }
 
}

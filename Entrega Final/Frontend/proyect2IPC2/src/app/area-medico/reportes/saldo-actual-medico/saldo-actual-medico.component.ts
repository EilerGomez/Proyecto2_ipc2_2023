import { Component, OnInit } from '@angular/core';
import { Medicos } from 'src/app/Modelo/Medicos';
import { ServiceReportesService } from 'src/app/Service/service-reportes.service';

@Component({
  selector: 'app-saldo-actual-medico',
  templateUrl: './saldo-actual-medico.component.html',
  styleUrls: ['./saldo-actual-medico.component.css']
})
export class SaldoActualMedicoComponent implements OnInit{
 constructor(private service:ServiceReportesService){}

  saldoActual:number=0

  ngOnInit(): void {
    var area = localStorage.getItem("area");
    let areaM:String = area ? area:'';
    let stringUser = localStorage.getItem('usuario');
    let medic: Medicos = stringUser ? JSON.parse(stringUser) : null;
    let userMedic = medic ? medic.userName : '';

    this.service.getSaldoActual(userMedic,areaM)
    .subscribe(data=>{
        this.saldoActual=data.saldo;
    }, error=>{
      console.log(error)
    })
  }

}

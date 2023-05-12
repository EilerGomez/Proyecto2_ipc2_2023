import { Component } from '@angular/core';
import { Medicos } from 'src/app/Modelo/Medicos';
import { ServiceReportesPacienteService } from 'src/app/Service/service-reportes-paciente.service';
import { ServiceReportesService } from 'src/app/Service/service-reportes.service';

@Component({
  selector: 'app-saldo-actual',
  templateUrl: './saldo-actual.component.html',
  styleUrls: ['./saldo-actual.component.css']
})
export class SaldoActualComponent {
  constructor(private service:ServiceReportesService){}
  saldoActual!:number
  ngOnInit(): void {
    var area = localStorage.getItem("area");
    let areaM:String = area ? area:'';
    let stringUser = localStorage.getItem('usuario');
    let medic: Medicos = stringUser ? JSON.parse(stringUser) : null;
    let userMedic = medic ? medic.userName : '';//es usuario pacientes

    this.service.getSaldoActual(userMedic,areaM)
    .subscribe(data=>{
        this.saldoActual=data.saldo;
    }, error=>{
      console.log(error)
    })
  }
}

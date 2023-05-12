import { Component } from '@angular/core';
import { Medicos } from 'src/app/Modelo/Medicos';
import { Pacientes } from 'src/app/Modelo/Paciente';
import { ServiceReportesService } from 'src/app/Service/service-reportes.service';
import { ServiceSaldosPacienteService } from 'src/app/Service/service-saldos-paciente.service';

@Component({
  selector: 'app-saldos',
  templateUrl: './saldos.component.html',
  styleUrls: ['./saldos.component.css']
})
export class SaldosComponent {

  constructor(private service:ServiceReportesService, private servicioRecargas:ServiceSaldosPacienteService){}
  mostrarRecargar:boolean=false
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

  recargar(){
    this.mostrarRecargar=true
  }

  guardarSaldo(saldo:string){
    var area = localStorage.getItem("area");
    let areaM:String = area ? area:'';
    let stringUser = localStorage.getItem('usuario');
    let medic: Pacientes = stringUser ? JSON.parse(stringUser) : null;
    let idPaciente = medic ? medic.idUsuario : 0;//es usuario pacientes
    this.servicioRecargas.postNewRecarga(idPaciente, parseInt(saldo))
    .subscribe(data=>{
      this.ngOnInit()
    })
  }
}

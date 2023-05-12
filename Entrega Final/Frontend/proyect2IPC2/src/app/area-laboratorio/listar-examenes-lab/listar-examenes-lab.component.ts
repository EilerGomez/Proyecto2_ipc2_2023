import { Component, OnInit } from '@angular/core';
import { ExamenesLabs } from 'src/app/Modelo/ExamenesLabs';
import { Laboratorios } from 'src/app/Modelo/Laboratorio';
import { ServiceExamenesService } from 'src/app/Service/service-examenes.service';

@Component({
  selector: 'app-listar-examenes-lab',
  templateUrl: './listar-examenes-lab.component.html',
  styleUrls: ['./listar-examenes-lab.component.css']
})
export class ListarExamenesLabComponent implements OnInit {
  constructor(private service: ServiceExamenesService) { }
  mostrarEditar: boolean = false
  examenes!: ExamenesLabs[]
  examenEdit!: ExamenesLabs
  ngOnInit(): void {
    let stringUser = localStorage.getItem('usuario');
    let medic: Laboratorios = stringUser ? JSON.parse(stringUser) : null;
    let idLab = medic ? medic.idUsuario : 0;
    console.log(idLab)
    this.service.getExamenesDeLabEspecifico(idLab).subscribe(data => {
      this.examenes = data;
    })
  }

  Editar(examen: ExamenesLabs) {
    this.mostrarEditar = true
    this.examenEdit = new ExamenesLabs(examen.idLaboratorio, examen.idExamen, examen.nombreExamen, examen.precio, examen.estado);
  }

  actualizarExamen(examen: ExamenesLabs, nuevoPrecio: string) {
    examen.precio = parseInt(nuevoPrecio);
    this.service.putExamenesLabs(examen)
      .subscribe(data => {
        console.log(JSON.stringify(data))
        this.ngOnInit()
      })
  }

  Reenviar(examen: ExamenesLabs) {
    examen.estado = "PENDIENTE";
    this.service.putExamenesLabs(examen)
    .subscribe(data => {
      console.log(JSON.stringify(data))
      this.ngOnInit()
    })
  }
}

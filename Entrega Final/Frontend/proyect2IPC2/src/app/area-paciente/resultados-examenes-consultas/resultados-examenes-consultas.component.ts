import { HttpClient } from '@angular/common/http';
import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { Consultas } from 'src/app/Modelo/Consultas';
import { ExamenesConsultas } from 'src/app/Modelo/ExamenesConsultas';
import { ServiceConsultasService } from 'src/app/Service/service-consultas.service';
import { ServiceExamenesConsultasService } from 'src/app/Service/service-examenes-consultas.service';

@Component({
  selector: 'app-resultados-examenes-consultas',
  templateUrl: './resultados-examenes-consultas.component.html',
  styleUrls: ['./resultados-examenes-consultas.component.css']
})
export class ResultadosExamenesConsultasComponent implements OnInit{
  constructor(private http:HttpClient, private servicioConsultas:ServiceConsultasService, private serviceExamenesC:ServiceExamenesConsultasService){}
  ngOnInit(): void {
    this.serviceExamenesC.getExamenesConsultas(this.consulta.idConsulta)
    .subscribe(data=>{
      this.examenesConsulta=data;
    })
  }

  @Input() consulta!:Consultas
  examenesConsulta!:ExamenesConsultas[]
  @Output() noMostrar= new EventEmitter<boolean>();
  mostrarBoton:boolean=false
  mostrarAleta:boolean=false
  jsonFile: File | undefined;

  onFileChange(event: any) {
      this.jsonFile = event.target.files[0];
      this.mostrarBoton=true
  }

  uploadJson() {
      if (!this.jsonFile) {
          return;
      }

      const apiUrl = 'http://localhost:8080/StudentsApi/files'; // Reemplaza con la URL de tu API
      const Url="http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletResultadoConsulta"

      const formData = new FormData();
      formData.append('archivoEntrada', this.jsonFile);

      this.http.post(`${Url}/${this.consulta.idConsulta}`, formData).subscribe(
          response => {
              console.log('Archivo pdf enviado exitosamente:', response);
              this.mostrarAleta=true
              this.consulta.estado="PENDIENTE_REVISION"
              this.servicioConsultas.putConsultaInformeYEstado(this.consulta).subscribe(
                data=>{
                  console.log(JSON.stringify(data))
                }
              )
          }
      );
  }

  Aceptar(){
    this.noMostrar.emit(false);
  }
}

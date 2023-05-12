import { HttpClient } from '@angular/common/http';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ExamenesSolicitud } from 'src/app/Modelo/ExamenesSolicitud';
import { Solicitudes } from 'src/app/Modelo/Solicitudes';
import { ServiceExamenesSolicitudService } from 'src/app/Service/service-examenes-solicitud.service';
import { ServiceResultadosSolicitudService } from 'src/app/Service/service-resultados-solicitud.service';
import { ServiceSolicitudesLABService } from 'src/app/Service/service-solicitudes-lab.service';

@Component({
  selector: 'app-resultados-solicitudes',
  templateUrl: './resultados-solicitudes.component.html',
  styleUrls: ['./resultados-solicitudes.component.css']
})
export class ResultadosSolicitudesComponent implements OnInit {
  openAlert = true
  @Input() solicitud!: Solicitudes
  examenesSolicitud!: ExamenesSolicitud[]
  @Output() aceptar = new EventEmitter<boolean>();
  constructor(private service: ServiceExamenesSolicitudService, private serviceResultados: ServiceResultadosSolicitudService
    ,private http: HttpClient, private serviceSolicitudeslab:ServiceSolicitudesLABService) { }
  ngOnInit(): void {
    this.service.getExamenesSolicitud(this.solicitud.idSolicitud)
      .subscribe(data => {
        this.examenesSolicitud = data
      })
  }

  Aceptar() {
    this.aceptar.emit(false)
  }


  //sobre agregar el archivo pdf
  mostrarBoton: boolean = false;
  mostrarBoton2:boolean=false
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
        const Url="http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletResultadosSolicitud"

        const formData = new FormData();
        formData.append('archivoEntrada', this.jsonFile);

        this.http.post(`${Url}/${this.solicitud.idSolicitud}`, formData).subscribe(
            response => {
                console.log('Archivo pdf enviado exitosamente:', response);
                this.mostrarBoton2=true
                this.serviceSolicitudeslab.putSolicitudLab(this.solicitud).subscribe(
                  data=>{
                    console.log(JSON.stringify(data))
                  }
                )
            }
        );
    }

    mostrarArchivo(){
      const Url="http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletResultadosSolicitud"
        this.http.get<Blob>(`${Url}/${this.solicitud.idSolicitud}`).subscribe(
        data=>{
          const blob = new Blob([data],{type:'application/pdf'});
          const url = URL.createObjectURL(blob)
          window.open(url,'_blank',)
        }
      )
    }
  

 

  
}

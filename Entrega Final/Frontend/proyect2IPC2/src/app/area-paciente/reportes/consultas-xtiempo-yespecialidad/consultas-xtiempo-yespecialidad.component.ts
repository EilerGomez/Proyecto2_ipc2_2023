import { Component, OnInit } from '@angular/core';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
import { Consultas } from 'src/app/Modelo/Consultas';
import { Especialidades } from 'src/app/Modelo/Especialidades';
import { Pacientes } from 'src/app/Modelo/Paciente';
import { ServiceReportesPacienteService } from 'src/app/Service/service-reportes-paciente.service';
import { ServiceService } from 'src/app/Service/service.EspecialidadesMedicas';

@Component({
  selector: 'app-consultas-xtiempo-yespecialidad',
  templateUrl: './consultas-xtiempo-yespecialidad.component.html',
  styleUrls: ['./consultas-xtiempo-yespecialidad.component.css']
})
export class ConsultasXtiempoYespecialidadComponent implements OnInit{
  constructor(private service:ServiceReportesPacienteService, private serviceEspecialidades:ServiceService){}
  ngOnInit(): void {
    this.serviceEspecialidades.getEspecialidadesDeLaAPP()
    .subscribe(data=>{
      this.especialdiades=data
    })
  }

  especialdiades!:Especialidades[]
  idpaciente!:number
  consultas!:Consultas[]
  mostrarTabla:boolean=false
  verConsultas(desde:String, hasta:String, idEsp:string){
    this.mostrarTabla=true
    let stringUser = localStorage.getItem('usuario');
    let medic: Pacientes = stringUser ? JSON.parse(stringUser) : null;
    let idPaciente = medic ? medic.idUsuario : 0;//es usuario pacientes
    this.idpaciente = idPaciente
    this.service.getConsultas(idPaciente,desde,hasta,parseInt(idEsp))
    .subscribe(data=>{
      this.consultas=data
    }, error=>{
      console.log(error)
      this.consultas=[]
    })
  }

  downloadPDF() {
    // Extraemos el
    const DATA = document.getElementById('tabla')!;
    const doc = new jsPDF('p', 'pt', 'a4');
    const options = {
      background: 'white',
      scale: 3
    };
    html2canvas(DATA, options).then((canvas) => {

      const img = canvas.toDataURL('image/PNG');

      // Add image Canvas to PDF
      const bufferX = 15;
      const bufferY = 15;
      const imgProps = (doc as any).getImageProperties(img);
      const pdfWidth = doc.internal.pageSize.getWidth() - 2 * bufferX;
      const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;
      doc.addImage(img, 'PNG', bufferX, bufferY, pdfWidth, pdfHeight, undefined, 'FAST');
      return doc;
    }).then((docResult) => {
      docResult.save(`consultasXTiempoYespecialidad.pdf`);
    });
  }
}

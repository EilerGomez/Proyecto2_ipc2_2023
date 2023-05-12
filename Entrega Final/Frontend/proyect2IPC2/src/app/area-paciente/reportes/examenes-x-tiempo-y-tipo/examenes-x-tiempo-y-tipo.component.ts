import { Component, OnInit } from '@angular/core';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
import { Examenes } from 'src/app/Modelo/Examenes';
import { Pacientes } from 'src/app/Modelo/Paciente';
import { ServiceExamenesService } from 'src/app/Service/service-examenes.service';
import { ServiceReportesPacienteService } from 'src/app/Service/service-reportes-paciente.service';

@Component({
  selector: 'app-examenes-x-tiempo-y-tipo',
  templateUrl: './examenes-x-tiempo-y-tipo.component.html',
  styleUrls: ['./examenes-x-tiempo-y-tipo.component.css']
})
export class ExamenesXTiempoYTipoComponent implements OnInit{
  constructor(private serviceExamenes:ServiceExamenesService, private servicioReportes: ServiceReportesPacienteService){}
  examenes!:Examenes[]
  examenesReport!:Examenes[]
  idpaciente!:number
  ngOnInit(): void {
    this.serviceExamenes.getExamenesDeLaApp()
    .subscribe(data=>{
      this.examenes=data
    })
  }


  mostrarTabla:boolean=false;
  verExamenes(desde:String, hasta:String, tipo:String){
    this.mostrarTabla=true
    let stringUser = localStorage.getItem('usuario');
    let medic: Pacientes = stringUser ? JSON.parse(stringUser) : null;
    let idPaciente = medic ? medic.idUsuario : 0;//es usuario pacientes
    this.idpaciente = idPaciente
    this.servicioReportes.getExamenes(idPaciente,desde,hasta,tipo)
    .subscribe(data=>{
      this.examenesReport = data;
    }, error=>{
      console.log(error)
      this.examenesReport=[]
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
      docResult.save(`examenesXtiempoYTipo.pdf`);
    });
  }
}

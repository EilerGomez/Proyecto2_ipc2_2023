import { Component, OnInit } from '@angular/core';
import { ServiceReportesLaboratorioService } from 'src/app/Service/service-reportes-laboratorio.service';
import { Top5PacientesMayorIngresoLab } from 'src/app/Modelo/Top5PacientesMIngreso(lab)';
import { Laboratorios } from 'src/app/Modelo/Laboratorio';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';

@Component({
  selector: 'app-top5pacientesmayoringreso',
  templateUrl: './top5pacientesmayoringreso.component.html',
  styleUrls: ['./top5pacientesmayoringreso.component.css']
})
export class Top5pacientesmayoringresoComponent {

  constructor(private service:ServiceReportesLaboratorioService){}
  

  report5PMI!:Top5PacientesMayorIngresoLab[]
  mostrarTabla:boolean=false
  MostrarReporte(desde:String, hasta:String){
    this.mostrarTabla=true    
    let stringUser = localStorage.getItem('usuario');
    let medic: Laboratorios = stringUser ? JSON.parse(stringUser) : null;
    let idLab = medic ? medic.idUsuario : 0;
    this.service.getPacientes(desde,hasta,idLab)
    .subscribe(data=>{
      this.report5PMI=data;
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
      docResult.save(`top5pacientesMayorIngreso.pdf`);
    });
  }
}

import { Component } from '@angular/core';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
import { Laboratorios } from 'src/app/Modelo/Laboratorio';
import { Top5ExamenesMIngreso } from 'src/app/Modelo/Top5ExamenesMIngreso';
import { ServiceReportesLaboratorioService } from 'src/app/Service/service-reportes-laboratorio.service';

@Component({
  selector: 'app-top5examenesmayoringreso',
  templateUrl: './top5examenesmayoringreso.component.html',
  styleUrls: ['./top5examenesmayoringreso.component.css']
})
export class Top5examenesmayoringresoComponent {
  constructor(private service:ServiceReportesLaboratorioService){}
  report5EMI!:Top5ExamenesMIngreso[]
  mostrarTabla:boolean=false;
  MostrarReporte(desde:String, hasta:String){
    this.mostrarTabla=true   
    let stringUser = localStorage.getItem('usuario');
    let medic: Laboratorios = stringUser ? JSON.parse(stringUser) : null;
    let idLab = medic ? medic.idUsuario : 0;
    this.service.getExamenes(desde,hasta,idLab)
    .subscribe(data=>{
      this.report5EMI=data;
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
      docResult.save(`topyexamenesMayorIngreso.pdf`);
    });
  }
}

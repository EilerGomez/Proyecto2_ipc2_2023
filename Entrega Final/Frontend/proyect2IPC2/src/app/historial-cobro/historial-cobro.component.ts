import { Component, OnInit } from '@angular/core';
import { cobroApp } from '../Modelo/cobroApp';
import { ServiceCobroAppService } from '../Service/service-cobro-app.service';
import { HttpClient } from '@angular/common/http';
import{jsPDF} from  'jspdf';
import * as $ from "jquery";
import html2canvas from 'html2canvas';

@Component({
  selector: 'app-historial-cobro',
  templateUrl: './historial-cobro.component.html',
  styleUrls: ['./historial-cobro.component.css']
})
export class HistorialCobroComponent implements OnInit {

  cobroApp!: cobroApp[]
  constructor(private service:ServiceCobroAppService, private http:HttpClient){}

  ngOnInit(): void {
    this.service.getHistorial().subscribe(data=>{
      this.cobroApp=data;
    })
  }

  Descargar(){
    this.service.exportarReportes().subscribe(
      response=>{
        const url = window.URL.createObjectURL(response)
        const a = document.createElement('a')
        a.href=url;
        a.download='reporte.pdf'
        document.body.appendChild(a)
        a.click();
        document.body.removeChild(a);
      }
    )
  }

  DescargarPDF(){
  }
  downloadPDF() {
    // Extraemos el
    const DATA = document.getElementById('htmlData')!;
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
      docResult.save(`${new Date().toISOString()}_hiatorialCobro.pdf`);
    });
  }
 
}

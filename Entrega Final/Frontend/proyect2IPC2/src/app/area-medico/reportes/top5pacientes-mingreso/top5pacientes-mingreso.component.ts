import { Component } from '@angular/core';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
import { Medicos } from 'src/app/Modelo/Medicos';
import { Reporte5PacientesMIngreso } from 'src/app/Modelo/Reporte5PacientesMIngreso';
import { ServiceReportesService } from 'src/app/Service/service-reportes.service';

@Component({
  selector: 'app-top5pacientes-mingreso',
  templateUrl: './top5pacientes-mingreso.component.html',
  styleUrls: ['./top5pacientes-mingreso.component.css']
})
export class Top5pacientesMingresoComponent{
  constructor(private service:ServiceReportesService){}
  report5PMI!:Reporte5PacientesMIngreso[]
  mostrarTabla:boolean=false

  MostrarReporte(desde:String, hasta:String){
    let stringUser = localStorage.getItem('usuario');
    let medic: Medicos = stringUser ? JSON.parse(stringUser) : null;
    let idmedico = medic ? medic.idUsuario : 0;

    this.service.getTop5PacientesMIngreso(desde,hasta,idmedico)
    .subscribe(data=>{
      this.report5PMI=data;
    },error=>{
      console.log(error)
    })
    this.mostrarTabla=true;
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
      docResult.save(`top5PacientesMIngreso.pdf`);
    });
  }
}

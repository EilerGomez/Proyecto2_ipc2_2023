import { Component, OnInit } from '@angular/core';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
import { HistorialRecargas } from 'src/app/Modelo/HistorialRecargas';
import { Pacientes } from 'src/app/Modelo/Paciente';
import { ServiceSaldosPacienteService } from 'src/app/Service/service-saldos-paciente.service';

@Component({
  selector: 'app-historial-recargas',
  templateUrl: './historial-recargas.component.html',
  styleUrls: ['./historial-recargas.component.css']
})
export class HistorialRecargasComponent implements OnInit{

  constructor(private service:ServiceSaldosPacienteService){}
  ngOnInit(): void {
    let stringUser = localStorage.getItem('usuario');
    let medic: Pacientes = stringUser ? JSON.parse(stringUser) : null;
    let idPaciente = medic ? medic.idUsuario : 0;//es usuario pacientes
    this.idpaciente = idPaciente
    this.service.getHistorialRecargas(idPaciente)
    .subscribe(data=>{
      this.recargas=data;
    })
  }
  recargas!:HistorialRecargas[]
  idpaciente!:number

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
      docResult.save(`HistorialRecargas.pdf`);
    });
  }

}

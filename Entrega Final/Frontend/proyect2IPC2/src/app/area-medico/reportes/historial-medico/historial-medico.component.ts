import { Component } from '@angular/core';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
import { Consultas } from 'src/app/Modelo/Consultas';
import { Examenes } from 'src/app/Modelo/Examenes';
import { Medicos } from 'src/app/Modelo/Medicos';
import { ServiceHistorialMedicoService } from 'src/app/Service/service-historial-medico.service';

@Component({
  selector: 'app-historial-medico',
  templateUrl: './historial-medico.component.html',
  styleUrls: ['./historial-medico.component.css']
})
export class HistorialMedicoComponent {
  constructor(private service: ServiceHistorialMedicoService) { }

  consultas: Consultas[]=[]
  examenes: Examenes[]=[]
  mostrarTablas: boolean = false

  verExamenes(idPaciente: string) {
    var IdPaciente1 = parseInt(idPaciente);
    this.service.getExamenesPaciente(IdPaciente1)
      .subscribe(data => {
        this.examenes = data;
        this.verConsultas(idPaciente)
      }, error => {
        console.log(error)
        this.examenes = [];
        this.verConsultas(idPaciente)
      });
    this.mostrarTablas = true
  }

  verConsultas(idPaciente: string) {
    let stringUser = localStorage.getItem('usuario');
    let medic: Medicos = stringUser ? JSON.parse(stringUser) : null;
    let idMedico = medic ? medic.idUsuario : 0;
    var IdPaciente1 = parseInt(idPaciente);
    this.service.getConsultasPaciente(IdPaciente1, idMedico)
      .subscribe(data => {
        this.consultas = data;
      }, error => {
        console.log(error)
        this.consultas = [];
      });
      this.mostrarTablas=true
  }

  downloadPDF() {
    // Extraemos el
    const DATA = document.getElementById('tablas')!;
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
      docResult.save(`HistorialMedico.pdf`);
    });
  }
 

}

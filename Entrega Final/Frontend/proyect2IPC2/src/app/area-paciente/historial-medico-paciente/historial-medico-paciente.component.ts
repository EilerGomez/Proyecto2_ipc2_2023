import { Component } from '@angular/core';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
import { Consultas } from 'src/app/Modelo/Consultas';
import { Examenes } from 'src/app/Modelo/Examenes';
import { Pacientes } from 'src/app/Modelo/Paciente';
import { ServiceHistorialMedicoPacienteService } from 'src/app/Service/service-historial-medico-paciente.service';

@Component({
  selector: 'app-historial-medico-paciente',
  templateUrl: './historial-medico-paciente.component.html',
  styleUrls: ['./historial-medico-paciente.component.css']
})
export class HistorialMedicoPacienteComponent {
  constructor(private service: ServiceHistorialMedicoPacienteService) { }

  mostrarTablas: boolean = false
  consultas!: Consultas[]
  examenes!: Examenes[]
  idpaciente!: number

  verHistorial(desde: String, hasta: String) {
    this.mostrarTablas=true
    var area = localStorage.getItem("area");
    let areaM: String = area ? area : '';
    let stringUser = localStorage.getItem('usuario');
    let medic: Pacientes = stringUser ? JSON.parse(stringUser) : null;
    let idPaciente = medic ? medic.idUsuario : 0;//es usuario pacientes
    this.idpaciente = idPaciente

    this.service.getConsultasPaciente(idPaciente, desde, hasta)
      .subscribe(data => {
        this.consultas = data;
        this.service.getExamenesPaciente(idPaciente, desde, hasta)
          .subscribe(data => {
            this.examenes = data;
          })
      }, error => {
        console.log(error)
        this.service.getExamenesPaciente(idPaciente, desde, hasta)
          .subscribe(data => {
            this.examenes = data;
          })
      })
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
      docResult.save(`HistorialMedicoPaciente.pdf`);
    });
  }
}

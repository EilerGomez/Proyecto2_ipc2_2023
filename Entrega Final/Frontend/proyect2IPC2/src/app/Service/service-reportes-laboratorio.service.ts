import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Top5PacientesMayorIngresoLab } from '../Modelo/Top5PacientesMIngreso(lab)';
import { Top5ExamenesMIngreso } from '../Modelo/Top5ExamenesMIngreso';

@Injectable({
  providedIn: 'root'
})
export class ServiceReportesLaboratorioService {

  constructor(private http:HttpClient) { }

  Url="http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletReportesLab"

  getPacientes(desde:String, hasta:String, idLab:number){
    return this.http.get<Top5PacientesMayorIngresoLab[]>(`${this.Url}/1/${desde}/${hasta}/${idLab}`)
  }

  getExamenes(desde:String, hasta:String, idLab:number){
    return this.http.get<Top5ExamenesMIngreso[]>(`${this.Url}/2/${desde}/${hasta}/${idLab}`)
  }
  
}

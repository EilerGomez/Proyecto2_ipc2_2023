import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ExamenesSolicitud } from '../Modelo/ExamenesSolicitud';

@Injectable({
  providedIn: 'root'
})
export class ServiceExamenesSolicitudService {

  constructor(private http:HttpClient) { }
  Url="http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletExamenesSolicitud"

  getExamenesSolicitud(idSolicitud:number){
    return this.http.get<ExamenesSolicitud[]>(`${this.Url}/${idSolicitud}`)
  }

  postNuevoExamenSolicitud(exSol:ExamenesSolicitud, porcentaje:number, idlab:number, idPaciente:number){
    return this.http.post<ExamenesSolicitud>(`${this.Url}/${porcentaje}/${idlab}/${idPaciente}`,exSol)
  }
}

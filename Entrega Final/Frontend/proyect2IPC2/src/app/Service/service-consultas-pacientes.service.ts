import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Consultas } from '../Modelo/Consultas';

@Injectable({
  providedIn: 'root'
})
export class ServiceConsultasPacientesService {

  constructor(private http:HttpClient) { }

  Url="http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletConsultasPacientes"

  getConsultasExamenPendiente(idPaciente:number){
    return this.http.get<Consultas[]>(`${this.Url}/EXAMEN_PENDIENTE/${idPaciente}`)
  }
}

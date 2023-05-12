import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Consultas } from '../Modelo/Consultas';
import { Examenes } from '../Modelo/Examenes';

@Injectable({
  providedIn: 'root'
})
export class ServiceHistorialMedicoService {

  constructor(private http:HttpClient) { }

  Url="http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletHistorialMedico"

  getConsultasPaciente(idPaciente:number, idMedico:number){
    return this.http.get<Consultas[]>(`${this.Url}/1/${idPaciente}/${idMedico}`);
  }

  getExamenesPaciente(idPaciente:number){
    return this.http.get<Examenes[]>(`${this.Url}/2/${idPaciente}/124`);
  }
}

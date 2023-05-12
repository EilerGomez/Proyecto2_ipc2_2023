import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Consultas } from '../Modelo/Consultas';
import { Examenes } from '../Modelo/Examenes';

@Injectable({
  providedIn: 'root'
})
export class ServiceHistorialMedicoPacienteService {

  constructor(private http:HttpClient) { }
  Url="http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletHistorialMedicoPaciente"

  getConsultasPaciente(idPaciente:number, desde:String, hasta:String){
    return this.http.get<Consultas[]>(`${this.Url}/1/${idPaciente}/${desde}/${hasta}`);
  }

  getExamenesPaciente(idPaciente:number, desde:String, hasta:String){
    return this.http.get<Examenes[]>(`${this.Url}/2/${idPaciente}/${desde}/${hasta}`);
  }
}

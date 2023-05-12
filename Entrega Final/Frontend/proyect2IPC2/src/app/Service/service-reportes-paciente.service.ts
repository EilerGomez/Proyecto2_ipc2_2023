import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Consultas } from '../Modelo/Consultas';
import { Examenes } from '../Modelo/Examenes';

@Injectable({
  providedIn: 'root'
})
export class ServiceReportesPacienteService {

  constructor(private http:HttpClient) { }

  UrlConsultas="http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletVerConsultasRealizadas"
  UrlExamens="http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletVerExamenesRealizadosP"

  getConsultas(idPaciente:number, desde:String, hasta:String, idEsp:number){
    return this.http.get<Consultas[]>(`${this.UrlConsultas}/${idPaciente}/${desde}/${hasta}/${idEsp}`)
  }

  getExamenes(idPaciente:number, desde:String, hasta:String, tipo:String){
    return this.http.get<Examenes[]>(`${this.UrlExamens}/${idPaciente}/${desde}/${hasta}/${tipo}`)
  }
}

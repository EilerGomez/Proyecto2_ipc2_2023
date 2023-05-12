import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Consultas } from '../Modelo/Consultas';
import { Cons } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServiceConsultasService {

  constructor(private http:HttpClient) { }

  Url = "http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletConsultas"
  getTodasConsultas(idMedico:number){
    return this.http.get<Consultas[]>(`${this.Url}/${idMedico}`);
  }

  getConsultasPorDia(idMedico:number, dia:String){
    return this.http.get<Consultas[]>(`${this.Url}/${idMedico}/PENDIENTE_REVISION/${dia}`);
  }

  getConsultasAgendadasPorDia(idMedico:number, dia:String){
    return this.http.get<Consultas[]>(`${this.Url}/${idMedico}/AGENDADA/${dia}`);
  }

  putConsultaInformeYEstado(consulta:Consultas){
    return this.http.put<Consultas>(this.Url,consulta);
  }

  postNewConsulta(consulta:Consultas){
    return this.http.post<Consultas>(this.Url,consulta)
  }
}

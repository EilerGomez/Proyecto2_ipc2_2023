import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ExamenesConsultas } from '../Modelo/ExamenesConsultas';

@Injectable({
  providedIn: 'root'
})
export class ServiceExamenesConsultasService {

  constructor(private http:HttpClient) { }


  Url="http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletExamenesConsulta"

  getExamenesConsultas(idConsulta:number){
    return this.http.get<ExamenesConsultas[]>(`${this.Url}/${idConsulta}`);
  }

  postExamenesConsulta(idConsulta:number, idExamen:number){
    return this.http.post(`${this.Url}/${idConsulta}/${idExamen}`,idConsulta);
  }
}

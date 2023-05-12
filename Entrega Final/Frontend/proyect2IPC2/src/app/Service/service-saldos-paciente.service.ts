import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HistorialRecargas } from '../Modelo/HistorialRecargas';

@Injectable({
  providedIn: 'root'
})
export class ServiceSaldosPacienteService {

  constructor(private http:HttpClient) { }

  Url="http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletRecargas"

  postNewRecarga(idPaciente:number, recarga:number){
   return this.http.post(`${this.Url}/${idPaciente}/${recarga}`,idPaciente);
  }

  getHistorialRecargas(idPaciente:number){
    return this.http.get<HistorialRecargas[]>(`${this.Url}/${idPaciente}`)
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Solicitudes } from '../Modelo/Solicitudes';

@Injectable({
  providedIn: 'root'
})
export class ServiceSolicitudesService {

  constructor(private http:HttpClient) { }
  Url="http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletSolicitudes"

  postNuevaSolicitud(solicitud:Solicitudes){
    return this.http.post<Solicitudes>(this.Url,solicitud);
  }

  getUltimaSolicitudHecha(idPaciente:number){
    return this.http.get<number>(`${this.Url}/${idPaciente}`)
  }

  getSolicitudesFinalizadas(idPaciente:number){
    return this.http.get<Solicitudes[]>(`${this.Url}/FINALIZADA/${idPaciente}`)
  }
}

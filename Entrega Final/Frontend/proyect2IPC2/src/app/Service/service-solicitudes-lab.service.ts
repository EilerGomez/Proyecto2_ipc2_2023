import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Solicitudes } from '../Modelo/Solicitudes';

@Injectable({
  providedIn: 'root'
})
export class ServiceSolicitudesLABService {

  constructor(private http:HttpClient) { }

  Url="http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletSolicitudeslab"

  getSolicitudesPendientes(idLab:number){
    return this.http.get<Solicitudes[]>(`${this.Url}/PENDIENTE/${idLab}`)
  }

  putSolicitudLab(solicitud:Solicitudes){
    return this.http.put<Solicitudes>(this.Url,solicitud)
  }
}

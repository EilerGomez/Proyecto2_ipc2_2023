import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ServiceResultadosSolicitudService {

  constructor(private http:HttpClient) { }

  Url="http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletResultadosSolicitud"

  postResultado(idSolicitud:number){
    return this.http.post(`${this.Url}/${idSolicitud}`, "hola");
  }
}

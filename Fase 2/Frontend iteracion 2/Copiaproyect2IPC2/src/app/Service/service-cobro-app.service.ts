import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { cobroApp } from '../Modelo/cobroApp';

@Injectable({
  providedIn: 'root'
})
export class ServiceCobroAppService {

  constructor(private http:HttpClient) { }

  Url = "http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletCobroApp";

  getHistorial(){
    return this.http.get<cobroApp[]>(this.Url);
  }

  getUltimoRegistro(){
    return this.http.get<cobroApp>(`${this.Url}/0`);
  }

  postNewPorcentaje(cobro:cobroApp){
    return this.http.post<cobroApp>(this.Url,cobro);
  }
}

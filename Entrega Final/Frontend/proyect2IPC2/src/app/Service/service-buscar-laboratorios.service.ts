import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Laboratorios } from '../Modelo/Laboratorio';

@Injectable({
  providedIn: 'root'
})
export class ServiceBuscarLaboratoriosService {

  constructor(private http:HttpClient) { }
  Url="http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletBuscarLaboratorios"

  getLaboratorios(nombre:String){
    return this.http.get<Laboratorios[]>(`${this.Url}/${nombre}`)
  }
}

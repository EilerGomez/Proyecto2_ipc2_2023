import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Medicos } from '../Modelo/Medicos';

@Injectable({
  providedIn: 'root'
})
export class ServiceBuscarMedicosService {

  constructor(private http:HttpClient) { }

  Url = "http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletBuscarMedicos"

  getMedicoPorEspecialidad(idEsp:String){
    return this.http.get<Medicos[]>(`${this.Url}/1/${idEsp}`)
  }

  getMedicoPorNombre(nombre:String){
    return this.http.get<Medicos[]>(`${this.Url}/2/${nombre}`)
  }
}

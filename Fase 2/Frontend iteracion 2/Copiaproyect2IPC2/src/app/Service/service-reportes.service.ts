import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Medicos } from '../Modelo/Medicos';
import { Reporte5PacientesMIngreso } from '../Modelo/Reporte5PacientesMIngreso';
import { Reporte5EspecialidadesMIngreso } from '../Modelo/Reporte5EspecialidadesMIngreso';

@Injectable({
  providedIn: 'root'
})
export class ServiceReportesService {

  constructor(private http:HttpClient) { }

  Url="http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletReportes"
  url="http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletReportes/2/2023-02-10/2023-04-10/124"

  getSaldoActual(usernameMedico:String, area:String){
    return this.http.get<Medicos>(`${this.Url}/1/${usernameMedico}/${area}`)
  }

  getTop5PacientesMIngreso(desde:String, hasta:String, idMedico:number){
    return this.http.get<Reporte5PacientesMIngreso[]>(`${this.Url}/2/${desde}/${hasta}/${idMedico}`)
  }
  getTop5EspecialidadesMIngreso(desde:String, hasta:String, idMedico:number){
    return this.http.get<Reporte5EspecialidadesMIngreso[]>(`${this.Url}/3/${desde}/${hasta}/${idMedico}`)
  }
}

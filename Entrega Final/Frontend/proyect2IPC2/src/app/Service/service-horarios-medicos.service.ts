import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HorariosMedicos } from '../Modelo/HorariosMedicos';

@Injectable({
  providedIn: 'root'
})
export class ServiceHorariosMedicosService {

  constructor(private http: HttpClient) { }

  UrlParter="http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletHorarios";

  postNewHorary(horarrio: HorariosMedicos){
    return this.http.post<HorariosMedicos>(`${this.UrlParter}/${horarrio.idMedico}`,horarrio);
    
  }

  getHorariosDeMedico(idMedico:number){
    return this.http.get<HorariosMedicos[]>(`${this.UrlParter}/${idMedico}`);
  }

  putHorario(horario:HorariosMedicos){
      return this.http.put<HorariosMedicos>(this.UrlParter,horario);
  }

}

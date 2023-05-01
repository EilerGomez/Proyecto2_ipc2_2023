import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { EspecialidadesMedicas } from '../Modelo/EspecialidadesMedicas';
import { Especialidades } from '../Modelo/Especialidades';
@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  //especialidad: EspecialidadesMedicas;
  constructor(private http: HttpClient) { 

  }
  Url = 'http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletEspecialidadesMedicas';
  UrPatern = 'http://localhost:8084/forProject2IPC2_2023_war_exploded/';
  getEspecialidades(){
    return this.http.get<EspecialidadesMedicas[]>(this.Url);
    
  }

  putEspecialidades(especialidad:EspecialidadesMedicas){
    return this.http.put<EspecialidadesMedicas>(this.Url, especialidad);
  }

  getEspecialidadesDeLaAPP(){
    return this.http.get<Especialidades[]>(`${this.UrPatern}ServletEspecialidades`);
  }

  postEspecialidadMedico(espe:EspecialidadesMedicas){
    return this.http.post<EspecialidadesMedicas>(this.Url,espe);
  }
  getEspecialidadesMedico(idMedico:number){
    return this.http.get<EspecialidadesMedicas[]>(`${this.Url}/TODAS/${idMedico}`);

  }


}

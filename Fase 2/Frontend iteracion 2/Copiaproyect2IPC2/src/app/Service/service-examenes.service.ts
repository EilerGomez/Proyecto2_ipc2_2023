import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ExamenesLabs } from '../Modelo/ExamenesLabs';
import { Examenes } from '../Modelo/Examenes';

@Injectable({
  providedIn: 'root'
})
export class ServiceExamenesService {

  constructor(private http: HttpClient) { }

  Url = "http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletExamenesLaboratorio"

  getExamenesLabs() {
    return this.http.get<ExamenesLabs[]>(this.Url);
  }

  putExamenesLabs(examen: ExamenesLabs) {
    return this.http.put<ExamenesLabs>(this.Url, examen);
  }

  UrlExamenesApp = "http://localhost:8084/forProject2IPC2_2023_war_exploded/ServletExamenes"
  getExamenesDeLaApp() {
    return this.http.get<Examenes[]>(this.UrlExamenesApp);
  }
}

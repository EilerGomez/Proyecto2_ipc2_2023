import { Injectable } from '@angular/core';
import { Login } from '../Modelo/Login';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Administrador } from '../Modelo/Administrador';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url = 'http://localhost:8084/forProject2IPC2_2023_war_exploded/servletLogin';
  admin: any;
  jsonContent: string;
  constructor(private http: HttpClient) {
    this.jsonContent='';
   }

   getLogin(login:Login ){
      this.jsonContent=login.toString();
      const httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
            })
          };
    /*this.http.post(this.url, this.jsonContent, httpOptions)
          .subscribe(response => console.log(response));*/
          this.jsonContent=JSON.stringify({ userName: login.getUserName(), password: login.getPassword(),area:login.getArea() });
          console.log(this.jsonContent);
          /*this.http.post(this.url, this.jsonContent, httpOptions)
          .subscribe(response => {console.log(response)          
        });*/

          
          return  this.http.post(this.url, this.jsonContent, httpOptions)
   }

   putCerrarConexionDB(cerrar:String){
      return this.http.put<String>(this.url,cerrar);
   }
}

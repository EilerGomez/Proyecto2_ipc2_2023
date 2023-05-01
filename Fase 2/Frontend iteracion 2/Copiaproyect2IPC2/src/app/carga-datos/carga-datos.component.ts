import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Component({
    selector: 'app-carga-datos',
    templateUrl: './carga-datos.component.html',
    styleUrls: ['./carga-datos.component.css']
})
export class CargaDatosComponent {

    botonActivo: boolean = false;
    openAlert: boolean = true;
    apiUrl = 'http://localhost:8084/forProject2IPC2_2023_war_exploded/cargaDatos'; // Coloca aquí la URL de tu API
    jsonContent: string;

    constructor(private http: HttpClient) {
        this.jsonContent = '';
    }

    onFileSelected(event: any): void {
        const file = event.target.files[0];
        const reader = new FileReader();
        reader.onload = (e) => {
            this.jsonContent = e.target!.result!.toString();
        };
        reader.readAsText(file);
    }

    sendJson(): void {
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };
        this.http.post(this.apiUrl, this.jsonContent, httpOptions)
            .subscribe(response => console.log(response));
        this.openAlert = false;
        this.botonActivo = true;
    }
}



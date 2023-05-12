import { Component, OnInit, ViewChild } from '@angular/core';
import { Route, Router } from '@angular/router';
import {LoginComponent} from './login/login.component'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  {
  @ViewChild(LoginComponent) login: any;
  title = 'proyect2IPC2';
  
}

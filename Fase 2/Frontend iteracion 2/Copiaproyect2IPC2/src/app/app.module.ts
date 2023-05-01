import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ListarComponent } from './Especialidadades/listar/listar.component';
import { FormsModule } from '@angular/forms'
import { ServiceService } from './Service/service.EspecialidadesMedicas'
import { HttpClientModule } from '@angular/common/http';
import { CargaDatosComponent } from './carga-datos/carga-datos.component'
import { LoginService } from './Service/login.service';
import { AreaAdministradorComponent } from './area-administrador/area-administrador.component';
import { EspacioAdminComponent } from './area-administrador/espacio-admin/espacio-admin.component';
import { ManagerHomepageComponent } from './manager-homepage/manager-homepage.component';
import { NavbarComponent } from './navbar/navbar.component'
import { ServiceExamenesService } from './Service/service-examenes.service'
import { ListarComponentLab } from './ExamenesLabs/listar/listar.component';
import { MonetizacionComponent } from './monetizacion/monetizacion.component';
import { ServiceCobroAppService } from './Service/service-cobro-app.service';
import { HistorialCobroComponent } from './historial-cobro/historial-cobro.component';
import { AreaMedicoComponent } from './area-medico/area-medico.component';
import { EspacioMedicoComponent } from './area-medico/espacio-medico/espacio-medico.component';
import { ListarEspecialidadesAppComponent } from './area-medico/listar-especialidades-app/listar-especialidades-app.component';
import { ListarEspMedicoComponent } from './area-medico/listar-esp-medico/listar-esp-medico.component';
import { NuevoHorarioComponent } from './area-medico/nuevo-horario/nuevo-horario.component'
import { ServiceHorariosMedicosService } from './Service/service-horarios-medicos.service';
import { ListarHorariosComponent } from './area-medico/listar-horarios/listar-horarios.component';
import { ConsultasPendientesComponent } from './area-medico/consultas/consultas-pendientes/consultas-pendientes.component'
import {ServiceConsultasService} from './Service/service-consultas.service';
import { TodasConsultasComponent } from './area-medico/consultas/todas-consultas/todas-consultas.component';
import { ConsultasAgendadasComponent } from './area-medico/consultas/consultas-agendadas/consultas-agendadas.component';
import { HistorialMedicoComponent } from './area-medico/reportes/historial-medico/historial-medico.component'
import {ServiceHistorialMedicoService} from './Service/service-historial-medico.service';
import { SaldoActualMedicoComponent } from './area-medico/reportes/saldo-actual-medico/saldo-actual-medico.component'
import {ServiceReportesService} from './Service/service-reportes.service';
import { Top5pacientesMingresoComponent } from './area-medico/reportes/top5pacientes-mingreso/top5pacientes-mingreso.component';
import { Top5especialidadesMingresoComponent } from './area-medico/reportes/top5especialidades-mingreso/top5especialidades-mingreso.component';
import { FinalizarConsultaComponent } from './area-medico/consultas/finalizar-consulta/finalizar-consulta.component';
import { AgregarExamenAConsultasComponent } from './area-medico/consultas/agregar-examen-a-consultas/agregar-examen-a-consultas.component';
import {ServiceExamenesConsultasService} from './Service/service-examenes-consultas.service'

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ListarComponent,
    CargaDatosComponent,
    AreaAdministradorComponent,
    EspacioAdminComponent,
    ManagerHomepageComponent,
    NavbarComponent,
    ListarComponentLab,
    MonetizacionComponent,
    HistorialCobroComponent,
    AreaMedicoComponent,
    EspacioMedicoComponent,
    ListarEspecialidadesAppComponent,
    ListarEspMedicoComponent,
    NuevoHorarioComponent,
    ListarHorariosComponent,
    ConsultasPendientesComponent,
    TodasConsultasComponent,
    ConsultasAgendadasComponent,
    HistorialMedicoComponent,
    SaldoActualMedicoComponent,
    Top5pacientesMingresoComponent,
    Top5especialidadesMingresoComponent,
    FinalizarConsultaComponent,
    AgregarExamenAConsultasComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [ServiceService, LoginService, ServiceExamenesService, ServiceCobroAppService,
    ServiceHorariosMedicosService,ServiceConsultasService,ServiceHistorialMedicoService,
    ServiceReportesService, ServiceExamenesConsultasService],
  bootstrap: [AppComponent]
})
export class AppModule { }

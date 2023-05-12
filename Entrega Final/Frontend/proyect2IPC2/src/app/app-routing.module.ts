import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListarComponent } from './Especialidadades/listar/listar.component';
import { AreaAdministradorComponent } from './area-administrador/area-administrador.component'
import { LoginComponent } from './login/login.component'
import { CargaDatosComponent } from './carga-datos/carga-datos.component'
import { ManagerHomepageComponent } from './manager-homepage/manager-homepage.component'
import { ListarComponentLab } from './ExamenesLabs/listar/listar.component'
import { MonetizacionComponent } from './monetizacion/monetizacion.component'
import { HistorialCobroComponent } from './historial-cobro/historial-cobro.component'
import { AreaMedicoComponent } from './area-medico/area-medico.component'
import { ListarEspecialidadesAppComponent } from './area-medico/listar-especialidades-app/listar-especialidades-app.component'
import { ListarEspMedicoComponent } from './area-medico/listar-esp-medico/listar-esp-medico.component'
import { NuevoHorarioComponent } from './area-medico/nuevo-horario/nuevo-horario.component'
import { ListarHorariosComponent } from './area-medico/listar-horarios/listar-horarios.component'
import { ConsultasPendientesComponent } from './area-medico/consultas/consultas-pendientes/consultas-pendientes.component'
import { TodasConsultasComponent } from './area-medico/consultas/todas-consultas/todas-consultas.component'
import { ConsultasAgendadasComponent } from './area-medico/consultas/consultas-agendadas/consultas-agendadas.component'
import { HistorialMedicoComponent } from './area-medico/reportes/historial-medico/historial-medico.component'
import { SaldoActualMedicoComponent } from './area-medico/reportes/saldo-actual-medico/saldo-actual-medico.component'
import { Top5pacientesMingresoComponent } from './area-medico/reportes/top5pacientes-mingreso/top5pacientes-mingreso.component'
import { Top5especialidadesMingresoComponent } from './area-medico/reportes/top5especialidades-mingreso/top5especialidades-mingreso.component'
import { AreaPacienteComponent } from './area-paciente/area-paciente.component'
import { AgendarConsultaComponent } from './area-paciente/agendar-consulta/agendar-consulta.component'
import { BuscarLaboratoriosComponent } from './area-paciente/buscar-laboratorios/buscar-laboratorios.component'
import { ListarSolicitudesFinalizadasComponent } from './area-paciente/listar-solicitudes-finalizadas/listar-solicitudes-finalizadas.component'
import { ListarConsultasExamenPendienteComponent } from './area-paciente/listar-consultas-examen_pendiente/listar-consultas-examen.pendiente.component'
import { SaldosComponent } from './area-paciente/saldos/saldos.component'
import { HistorialMedicoPacienteComponent } from './area-paciente/historial-medico-paciente/historial-medico-paciente.component'
import { HistorialRecargasComponent } from './area-paciente/historial-recargas/historial-recargas.component'
import { ConsultasXtiempoYespecialidadComponent } from './area-paciente/reportes/consultas-xtiempo-yespecialidad/consultas-xtiempo-yespecialidad.component'
import { ExamenesXTiempoYTipoComponent } from './area-paciente/reportes/examenes-x-tiempo-y-tipo/examenes-x-tiempo-y-tipo.component'
import { AreaLaboratorioComponent } from './area-laboratorio/area-laboratorio.component'
import { NuevoExamenComponent } from './area-laboratorio/nuevo-examen/nuevo-examen.component'
import { ListarExamenesLabComponent } from './area-laboratorio/listar-examenes-lab/listar-examenes-lab.component'
import { ListarSolicitudesPendientesComponent } from './area-laboratorio/listar-solicitudes-pendientes/listar-solicitudes-pendientes.component'
import { SaldoActualComponent } from './area-laboratorio/reportes/saldo-actual/saldo-actual.component'
import { Top5pacientesmayoringresoComponent } from './area-laboratorio/reportes/top5pacientesmayoringreso/top5pacientesmayoringreso.component'
import { Top5examenesmayoringresoComponent } from './area-laboratorio/reportes/top5examenesmayoringreso/top5examenesmayoringreso.component'


const routes: Routes = [

  { path: '', redirectTo: 'homepage', pathMatch: 'full' },
  { path: 'homepage', component: ManagerHomepageComponent },
  {
    path: 'areaAdministrador', component: AreaAdministradorComponent,
    children: [
      { path: 'listar', component: ListarComponent },
      { path: 'cargaDatos', component: CargaDatosComponent },
      { path: 'listarExamenes', component: ListarComponentLab },
      { path: 'monetizacion', component: MonetizacionComponent },
      { path: 'historialCobro', component: HistorialCobroComponent }
    ]
  },
  { path: 'login', component: LoginComponent },
  {
    path: 'areaMedico', component: AreaMedicoComponent,
    children: [
      { path: 'listarEspecialidadesApp', component: ListarEspecialidadesAppComponent },
      { path: 'listarEspecialidadesMedico', component: ListarEspMedicoComponent },
      { path: 'nuevoHorarrio', component: NuevoHorarioComponent },
      { path: 'listaHorarios', component: ListarHorariosComponent },
      { path: 'consultasPendientes', component: ConsultasPendientesComponent },
      { path: 'todasConsultas', component: TodasConsultasComponent },
      { path: 'consultasAgendadas', component: ConsultasAgendadasComponent },
      { path: 'historialMedico', component: HistorialMedicoComponent },
      { path: 'saldoActual', component: SaldoActualMedicoComponent },
      { path: 'top5PacientesMI', component: Top5pacientesMingresoComponent },
      { path: 'top5EspecialidadesMI', component: Top5especialidadesMingresoComponent },
    ]
  },
  {
    path: 'areaPaciente', component: AreaPacienteComponent,
    children: [
      { path: 'agendarConsulta', component: AgendarConsultaComponent },
      { path: 'buscarLaboratorios', component: BuscarLaboratoriosComponent },
      { path: 'listarSolicitudesFinalizadas', component: ListarSolicitudesFinalizadasComponent },
      { path: 'listarConsultasExamenPendiente', component: ListarConsultasExamenPendienteComponent },
      { path: 'saldos', component: SaldosComponent },
      { path: 'historialMedicopaciente', component: HistorialMedicoPacienteComponent },
      { path: 'historialRecargas', component: HistorialRecargasComponent },
      { path: 'consultasXTiempoYespecialidad', component: ConsultasXtiempoYespecialidadComponent },
      { path: 'examenesXTiempoyTipo', component: ExamenesXTiempoYTipoComponent }
    ]
  },
  {
    path: 'areaLaboratorio', component: AreaLaboratorioComponent,
    children:[
      { path: 'nuevaSolicitudExamen', component: NuevoExamenComponent },
      { path: 'listarExamenesLab', component: ListarExamenesLabComponent },
      { path: 'listarSolicitudesPendientes', component: ListarSolicitudesPendientesComponent },
      { path: 'saldoActualLab', component: SaldoActualComponent },
      { path: 'top5PacientesmayorIngresoReport', component: Top5pacientesmayoringresoComponent },
      { path: 'top5ExamenesmayorIngresoReport', component: Top5examenesmayoringresoComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

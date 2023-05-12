import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarSolicitudesPendientesComponent } from './listar-solicitudes-pendientes.component';

describe('ListarSolicitudesPendientesComponent', () => {
  let component: ListarSolicitudesPendientesComponent;
  let fixture: ComponentFixture<ListarSolicitudesPendientesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListarSolicitudesPendientesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarSolicitudesPendientesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarSolicitudesFinalizadasComponent } from './listar-solicitudes-finalizadas.component';

describe('ListarSolicitudesFinalizadasComponent', () => {
  let component: ListarSolicitudesFinalizadasComponent;
  let fixture: ComponentFixture<ListarSolicitudesFinalizadasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListarSolicitudesFinalizadasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarSolicitudesFinalizadasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

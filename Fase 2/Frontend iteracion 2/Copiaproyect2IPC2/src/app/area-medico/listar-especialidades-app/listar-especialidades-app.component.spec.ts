import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarEspecialidadesAppComponent } from './listar-especialidades-app.component';

describe('ListarEspecialidadesAppComponent', () => {
  let component: ListarEspecialidadesAppComponent;
  let fixture: ComponentFixture<ListarEspecialidadesAppComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListarEspecialidadesAppComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarEspecialidadesAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

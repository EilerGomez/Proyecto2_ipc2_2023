import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultadosExamenesSolicitudComponent } from './resultados-examenes-solicitud.component';

describe('ResultadosExamenesSolicitudComponent', () => {
  let component: ResultadosExamenesSolicitudComponent;
  let fixture: ComponentFixture<ResultadosExamenesSolicitudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResultadosExamenesSolicitudComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResultadosExamenesSolicitudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

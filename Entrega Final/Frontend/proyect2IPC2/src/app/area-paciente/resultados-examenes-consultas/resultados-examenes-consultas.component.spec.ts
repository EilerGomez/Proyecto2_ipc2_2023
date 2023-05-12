import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultadosExamenesConsultasComponent } from './resultados-examenes-consultas.component';

describe('ResultadosExamenesConsultasComponent', () => {
  let component: ResultadosExamenesConsultasComponent;
  let fixture: ComponentFixture<ResultadosExamenesConsultasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResultadosExamenesConsultasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResultadosExamenesConsultasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

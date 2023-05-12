import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistorialMedicoPacienteComponent } from './historial-medico-paciente.component';

describe('HistorialMedicoPacienteComponent', () => {
  let component: HistorialMedicoPacienteComponent;
  let fixture: ComponentFixture<HistorialMedicoPacienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistorialMedicoPacienteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistorialMedicoPacienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

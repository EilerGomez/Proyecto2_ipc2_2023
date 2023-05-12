import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EspacioPacienteComponent } from './espacio-paciente.component';

describe('EspacioPacienteComponent', () => {
  let component: EspacioPacienteComponent;
  let fixture: ComponentFixture<EspacioPacienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EspacioPacienteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EspacioPacienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

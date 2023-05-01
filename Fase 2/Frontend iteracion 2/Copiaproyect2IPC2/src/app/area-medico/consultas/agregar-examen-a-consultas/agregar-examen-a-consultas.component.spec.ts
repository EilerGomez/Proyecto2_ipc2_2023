import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgregarExamenAConsultasComponent } from './agregar-examen-a-consultas.component';

describe('AgregarExamenAConsultasComponent', () => {
  let component: AgregarExamenAConsultasComponent;
  let fixture: ComponentFixture<AgregarExamenAConsultasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgregarExamenAConsultasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AgregarExamenAConsultasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

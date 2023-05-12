import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultasXtiempoYespecialidadComponent } from './consultas-xtiempo-yespecialidad.component';

describe('ConsultasXtiempoYespecialidadComponent', () => {
  let component: ConsultasXtiempoYespecialidadComponent;
  let fixture: ComponentFixture<ConsultasXtiempoYespecialidadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultasXtiempoYespecialidadComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultasXtiempoYespecialidadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

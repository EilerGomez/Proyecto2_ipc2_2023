import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EspacioLaboratorioComponent } from './espacio-laboratorio.component';

describe('EspacioLaboratorioComponent', () => {
  let component: EspacioLaboratorioComponent;
  let fixture: ComponentFixture<EspacioLaboratorioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EspacioLaboratorioComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EspacioLaboratorioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

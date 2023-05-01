import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EspacioMedicoComponent } from './espacio-medico.component';

describe('EspacioMedicoComponent', () => {
  let component: EspacioMedicoComponent;
  let fixture: ComponentFixture<EspacioMedicoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EspacioMedicoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EspacioMedicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

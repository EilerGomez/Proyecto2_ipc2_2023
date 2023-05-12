import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgregarExamenesASolicitudComponent } from './agregar-examenes-a-solicitud.component';

describe('AgregarExamenesASolicitudComponent', () => {
  let component: AgregarExamenesASolicitudComponent;
  let fixture: ComponentFixture<AgregarExamenesASolicitudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgregarExamenesASolicitudComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AgregarExamenesASolicitudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

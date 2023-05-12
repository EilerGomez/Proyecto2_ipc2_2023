import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultadosSolicitudesComponent } from './resultados-solicitudes.component';

describe('ResultadosSolicitudesComponent', () => {
  let component: ResultadosSolicitudesComponent;
  let fixture: ComponentFixture<ResultadosSolicitudesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResultadosSolicitudesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResultadosSolicitudesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

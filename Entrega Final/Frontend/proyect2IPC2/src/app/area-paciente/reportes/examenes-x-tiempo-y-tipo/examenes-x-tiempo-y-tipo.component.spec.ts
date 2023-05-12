import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamenesXTiempoYTipoComponent } from './examenes-x-tiempo-y-tipo.component';

describe('ExamenesXTiempoYTipoComponent', () => {
  let component: ExamenesXTiempoYTipoComponent;
  let fixture: ComponentFixture<ExamenesXTiempoYTipoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExamenesXTiempoYTipoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExamenesXTiempoYTipoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

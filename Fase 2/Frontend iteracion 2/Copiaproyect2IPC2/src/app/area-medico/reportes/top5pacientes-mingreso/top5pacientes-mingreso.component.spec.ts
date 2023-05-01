import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Top5pacientesMingresoComponent } from './top5pacientes-mingreso.component';

describe('Top5pacientesMingresoComponent', () => {
  let component: Top5pacientesMingresoComponent;
  let fixture: ComponentFixture<Top5pacientesMingresoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Top5pacientesMingresoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Top5pacientesMingresoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

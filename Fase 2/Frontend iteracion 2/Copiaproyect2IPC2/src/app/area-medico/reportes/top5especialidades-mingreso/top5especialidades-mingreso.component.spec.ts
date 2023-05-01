import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Top5especialidadesMingresoComponent } from './top5especialidades-mingreso.component';

describe('Top5especialidadesMingresoComponent', () => {
  let component: Top5especialidadesMingresoComponent;
  let fixture: ComponentFixture<Top5especialidadesMingresoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Top5especialidadesMingresoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Top5especialidadesMingresoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

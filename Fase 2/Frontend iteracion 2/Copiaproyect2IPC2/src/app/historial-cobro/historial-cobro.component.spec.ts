import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistorialCobroComponent } from './historial-cobro.component';

describe('HistorialCobroComponent', () => {
  let component: HistorialCobroComponent;
  let fixture: ComponentFixture<HistorialCobroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistorialCobroComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistorialCobroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

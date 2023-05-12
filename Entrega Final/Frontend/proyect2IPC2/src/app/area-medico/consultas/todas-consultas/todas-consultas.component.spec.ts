import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodasConsultasComponent } from './todas-consultas.component';

describe('TodasConsultasComponent', () => {
  let component: TodasConsultasComponent;
  let fixture: ComponentFixture<TodasConsultasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TodasConsultasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TodasConsultasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

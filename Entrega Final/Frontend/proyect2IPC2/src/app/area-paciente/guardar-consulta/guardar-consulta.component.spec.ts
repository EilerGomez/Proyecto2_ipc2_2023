import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuardarConsultaComponent } from './guardar-consulta.component';

describe('GuardarConsultaComponent', () => {
  let component: GuardarConsultaComponent;
  let fixture: ComponentFixture<GuardarConsultaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GuardarConsultaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GuardarConsultaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

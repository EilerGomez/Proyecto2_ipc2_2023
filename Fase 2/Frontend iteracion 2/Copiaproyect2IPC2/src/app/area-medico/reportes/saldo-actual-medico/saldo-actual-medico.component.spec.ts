import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaldoActualMedicoComponent } from './saldo-actual-medico.component';

describe('SaldoActualMedicoComponent', () => {
  let component: SaldoActualMedicoComponent;
  let fixture: ComponentFixture<SaldoActualMedicoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SaldoActualMedicoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SaldoActualMedicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

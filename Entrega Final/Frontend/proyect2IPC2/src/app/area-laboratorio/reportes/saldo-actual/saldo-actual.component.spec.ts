import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaldoActualComponent } from './saldo-actual.component';

describe('SaldoActualComponent', () => {
  let component: SaldoActualComponent;
  let fixture: ComponentFixture<SaldoActualComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SaldoActualComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SaldoActualComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

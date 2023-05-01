import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MonetizacionComponent } from './monetizacion.component';

describe('MonetizacionComponent', () => {
  let component: MonetizacionComponent;
  let fixture: ComponentFixture<MonetizacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MonetizacionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MonetizacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

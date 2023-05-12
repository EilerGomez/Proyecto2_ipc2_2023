import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuscarLaboratoriosComponent } from './buscar-laboratorios.component';

describe('BuscarLaboratoriosComponent', () => {
  let component: BuscarLaboratoriosComponent;
  let fixture: ComponentFixture<BuscarLaboratoriosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BuscarLaboratoriosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BuscarLaboratoriosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

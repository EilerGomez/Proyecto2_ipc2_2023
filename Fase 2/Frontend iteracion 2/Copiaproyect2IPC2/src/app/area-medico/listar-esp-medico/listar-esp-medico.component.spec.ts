import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarEspMedicoComponent } from './listar-esp-medico.component';

describe('ListarEspMedicoComponent', () => {
  let component: ListarEspMedicoComponent;
  let fixture: ComponentFixture<ListarEspMedicoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListarEspMedicoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarEspMedicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

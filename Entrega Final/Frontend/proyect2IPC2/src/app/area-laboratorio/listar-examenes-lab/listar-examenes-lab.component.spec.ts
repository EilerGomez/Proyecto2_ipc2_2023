import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarExamenesLabComponent } from './listar-examenes-lab.component';

describe('ListarExamenesLabComponent', () => {
  let component: ListarExamenesLabComponent;
  let fixture: ComponentFixture<ListarExamenesLabComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListarExamenesLabComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarExamenesLabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

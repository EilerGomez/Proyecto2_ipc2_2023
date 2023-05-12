import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AreaLaboratorioComponent } from './area-laboratorio.component';

describe('AreaLaboratorioComponent', () => {
  let component: AreaLaboratorioComponent;
  let fixture: ComponentFixture<AreaLaboratorioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AreaLaboratorioComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AreaLaboratorioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

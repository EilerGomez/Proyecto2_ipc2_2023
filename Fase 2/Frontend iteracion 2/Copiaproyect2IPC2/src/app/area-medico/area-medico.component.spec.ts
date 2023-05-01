import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AreaMedicoComponent } from './area-medico.component';

describe('AreaMedicoComponent', () => {
  let component: AreaMedicoComponent;
  let fixture: ComponentFixture<AreaMedicoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AreaMedicoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AreaMedicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

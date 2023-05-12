import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AreaAdministradorComponent } from './area-administrador.component';

describe('AreaAdministradorComponent', () => {
  let component: AreaAdministradorComponent;
  let fixture: ComponentFixture<AreaAdministradorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AreaAdministradorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AreaAdministradorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

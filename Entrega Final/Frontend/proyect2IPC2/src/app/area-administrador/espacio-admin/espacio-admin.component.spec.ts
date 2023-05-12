import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EspacioAdminComponent } from './espacio-admin.component';

describe('EspacioAdminComponent', () => {
  let component: EspacioAdminComponent;
  let fixture: ComponentFixture<EspacioAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EspacioAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EspacioAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Top5examenesmayoringresoComponent } from './top5examenesmayoringreso.component';

describe('Top5examenesmayoringresoComponent', () => {
  let component: Top5examenesmayoringresoComponent;
  let fixture: ComponentFixture<Top5examenesmayoringresoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Top5examenesmayoringresoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Top5examenesmayoringresoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

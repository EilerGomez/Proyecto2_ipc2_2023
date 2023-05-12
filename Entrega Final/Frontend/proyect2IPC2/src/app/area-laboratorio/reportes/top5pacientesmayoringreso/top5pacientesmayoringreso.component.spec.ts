import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Top5pacientesmayoringresoComponent } from './top5pacientesmayoringreso.component';

describe('Top5pacientesmayoringresoComponent', () => {
  let component: Top5pacientesmayoringresoComponent;
  let fixture: ComponentFixture<Top5pacientesmayoringresoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Top5pacientesmayoringresoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Top5pacientesmayoringresoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

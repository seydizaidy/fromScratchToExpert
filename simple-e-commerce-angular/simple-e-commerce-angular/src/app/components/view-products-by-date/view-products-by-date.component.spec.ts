import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewProductsByDateComponent } from './view-products-by-date.component';

describe('ViewProductsByDateComponent', () => {
  let component: ViewProductsByDateComponent;
  let fixture: ComponentFixture<ViewProductsByDateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewProductsByDateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewProductsByDateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

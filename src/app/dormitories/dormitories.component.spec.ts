import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DormitoriesComponent } from './dormitories.component';

describe('DormitoriesComponent', () => {
  let component: DormitoriesComponent;
  let fixture: ComponentFixture<DormitoriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DormitoriesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DormitoriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewCandidacyByofferComponent } from './view-candidacy-byoffer.component';

describe('ViewCandidacyByofferComponent', () => {
  let component: ViewCandidacyByofferComponent;
  let fixture: ComponentFixture<ViewCandidacyByofferComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewCandidacyByofferComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewCandidacyByofferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

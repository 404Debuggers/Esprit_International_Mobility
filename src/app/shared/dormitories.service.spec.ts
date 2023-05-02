import { TestBed } from '@angular/core/testing';

import { DormitoriesService } from './dormitories.service';

describe('DormitoriesService', () => {
  let service: DormitoriesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DormitoriesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

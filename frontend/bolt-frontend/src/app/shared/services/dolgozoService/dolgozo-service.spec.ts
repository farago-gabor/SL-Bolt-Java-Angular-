import { TestBed } from '@angular/core/testing';

import { DolgozoService } from './dolgozo-service';

describe('DolgozoService', () => {
  let service: DolgozoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DolgozoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

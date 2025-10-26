import { TestBed } from '@angular/core/testing';

import { TevekenysegService } from './tevekenyseg-service';

describe('TevekenysegService', () => {
  let service: TevekenysegService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TevekenysegService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

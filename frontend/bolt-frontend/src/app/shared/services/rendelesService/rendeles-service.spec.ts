import { TestBed } from '@angular/core/testing';

import { RendelesService } from './rendeles-service';

describe('Rendeles', () => {
  let service: RendelesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RendelesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

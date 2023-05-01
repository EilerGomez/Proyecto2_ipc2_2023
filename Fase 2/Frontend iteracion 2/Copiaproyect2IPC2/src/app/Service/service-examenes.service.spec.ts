import { TestBed } from '@angular/core/testing';

import { ServiceExamenesService } from './service-examenes.service';

describe('ServiceExamenesService', () => {
  let service: ServiceExamenesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceExamenesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

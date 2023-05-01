import { TestBed } from '@angular/core/testing';

import { ServiceCobroAppService } from './service-cobro-app.service';

describe('ServiceCobroAppService', () => {
  let service: ServiceCobroAppService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceCobroAppService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

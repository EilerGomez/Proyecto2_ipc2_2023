import { TestBed } from '@angular/core/testing';

import { ServiceSolicitudesLABService } from './service-solicitudes-lab.service';

describe('ServiceSolicitudesLABService', () => {
  let service: ServiceSolicitudesLABService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceSolicitudesLABService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

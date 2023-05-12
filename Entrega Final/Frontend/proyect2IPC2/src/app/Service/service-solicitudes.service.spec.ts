import { TestBed } from '@angular/core/testing';

import { ServiceSolicitudesService } from './service-solicitudes.service';

describe('ServiceSolicitudesService', () => {
  let service: ServiceSolicitudesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceSolicitudesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { ServiceExamenesSolicitudService } from './service-examenes-solicitud.service';

describe('ServiceExamenesSolicitudService', () => {
  let service: ServiceExamenesSolicitudService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceExamenesSolicitudService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

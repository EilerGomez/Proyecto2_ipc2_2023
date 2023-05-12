import { TestBed } from '@angular/core/testing';

import { ServiceResultadosSolicitudService } from './service-resultados-solicitud.service';

describe('ServiceResultadosSolicitudService', () => {
  let service: ServiceResultadosSolicitudService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceResultadosSolicitudService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

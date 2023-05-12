import { TestBed } from '@angular/core/testing';

import { ServiceReportesPacienteService } from './service-reportes-paciente.service';

describe('ServiceReportesPacienteService', () => {
  let service: ServiceReportesPacienteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceReportesPacienteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { ServiceConsultasPacientesService } from './service-consultas-pacientes.service';

describe('ServiceConsultasPacientesService', () => {
  let service: ServiceConsultasPacientesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceConsultasPacientesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

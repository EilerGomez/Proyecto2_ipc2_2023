import { TestBed } from '@angular/core/testing';

import { ServiceSaldosPacienteService } from './service-saldos-paciente.service';

describe('ServiceSaldosPacienteService', () => {
  let service: ServiceSaldosPacienteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceSaldosPacienteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

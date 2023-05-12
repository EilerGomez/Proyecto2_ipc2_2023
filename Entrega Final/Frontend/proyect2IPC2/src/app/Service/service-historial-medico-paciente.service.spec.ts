import { TestBed } from '@angular/core/testing';

import { ServiceHistorialMedicoPacienteService } from './service-historial-medico-paciente.service';

describe('ServiceHistorialMedicoPacienteService', () => {
  let service: ServiceHistorialMedicoPacienteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceHistorialMedicoPacienteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

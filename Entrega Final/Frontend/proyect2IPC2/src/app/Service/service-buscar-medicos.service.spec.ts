import { TestBed } from '@angular/core/testing';

import { ServiceBuscarMedicosService } from './service-buscar-medicos.service';

describe('ServiceBuscarMedicosService', () => {
  let service: ServiceBuscarMedicosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceBuscarMedicosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

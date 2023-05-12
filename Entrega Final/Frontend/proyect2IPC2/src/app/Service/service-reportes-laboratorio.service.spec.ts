import { TestBed } from '@angular/core/testing';

import { ServiceReportesLaboratorioService } from './service-reportes-laboratorio.service';

describe('ServiceReportesLaboratorioService', () => {
  let service: ServiceReportesLaboratorioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceReportesLaboratorioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

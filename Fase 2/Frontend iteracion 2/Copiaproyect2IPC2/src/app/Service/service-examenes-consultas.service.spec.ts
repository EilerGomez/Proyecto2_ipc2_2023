import { TestBed } from '@angular/core/testing';

import { ServiceExamenesConsultasService } from './service-examenes-consultas.service';

describe('ServiceExamenesConsultasService', () => {
  let service: ServiceExamenesConsultasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceExamenesConsultasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { PhoneStatusService } from './phone-status.service';

describe('PhoneStatusService', () => {
  let service: PhoneStatusService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PhoneStatusService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { Animal1Service } from './animal1.service';

describe('Animal1Service', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: Animal1Service = TestBed.get(Animal1Service);
    expect(service).toBeTruthy();
  });
});

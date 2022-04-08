import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PhoneStatusComponent } from './phone-status.component';

describe('PhoneStatusComponent', () => {
  let component: PhoneStatusComponent;
  let fixture: ComponentFixture<PhoneStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PhoneStatusComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PhoneStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

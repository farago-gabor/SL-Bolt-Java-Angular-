import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DolgozoKezelo } from './dolgozo-kezelo';

describe('DolgozoKezelo', () => {
  let component: DolgozoKezelo;
  let fixture: ComponentFixture<DolgozoKezelo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DolgozoKezelo]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DolgozoKezelo);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

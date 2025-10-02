import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Rendelesek } from './rendelesek';

describe('Rendelesek', () => {
  let component: Rendelesek;
  let fixture: ComponentFixture<Rendelesek>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Rendelesek]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Rendelesek);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

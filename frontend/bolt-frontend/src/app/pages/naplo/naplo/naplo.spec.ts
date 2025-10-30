import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Naplo } from './naplo';

describe('Naplo', () => {
  let component: Naplo;
  let fixture: ComponentFixture<Naplo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Naplo]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Naplo);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

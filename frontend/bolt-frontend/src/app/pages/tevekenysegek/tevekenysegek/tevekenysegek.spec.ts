import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Tevekenysegek } from './tevekenysegek';

describe('Tevekenysegek', () => {
  let component: Tevekenysegek;
  let fixture: ComponentFixture<Tevekenysegek>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Tevekenysegek]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Tevekenysegek);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FragranceCardSmallComponent } from './fragrance-card-small.component';

describe('FragranceCardSmallComponent', () => {
  let component: FragranceCardSmallComponent;
  let fixture: ComponentFixture<FragranceCardSmallComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FragranceCardSmallComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FragranceCardSmallComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

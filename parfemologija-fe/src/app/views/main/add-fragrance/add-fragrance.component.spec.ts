import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFragranceComponent } from './add-fragrance.component';

describe('AddFragranceComponent', () => {
  let component: AddFragranceComponent;
  let fixture: ComponentFixture<AddFragranceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddFragranceComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddFragranceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

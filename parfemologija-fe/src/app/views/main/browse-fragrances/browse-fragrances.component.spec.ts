import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowseFragrancesComponent } from './browse-fragrances.component';

describe('BrowseFragrancesComponent', () => {
  let component: BrowseFragrancesComponent;
  let fixture: ComponentFixture<BrowseFragrancesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BrowseFragrancesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BrowseFragrancesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

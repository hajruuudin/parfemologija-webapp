import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FragranceOverviewComponent } from './fragrance-overview.component';

describe('FragranceOverviewComponent', () => {
  let component: FragranceOverviewComponent;
  let fixture: ComponentFixture<FragranceOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FragranceOverviewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FragranceOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

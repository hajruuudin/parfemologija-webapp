import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleCardSmallComponent } from './article-card-small.component';

describe('ArticleCardSmallComponent', () => {
  let component: ArticleCardSmallComponent;
  let fixture: ComponentFixture<ArticleCardSmallComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArticleCardSmallComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArticleCardSmallComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

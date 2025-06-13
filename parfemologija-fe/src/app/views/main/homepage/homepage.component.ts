// src/app/views/main/homepage/homepage.component.ts
import { Component, OnInit } from '@angular/core';
import { NgIf, NgFor } from '@angular/common';
import { NgxSpinnerComponent, NgxSpinnerService } from 'ngx-spinner';

import { HeadlineComponent }      from '../../../components/headline/headline.component';
import { ArticleCardComponent }   from '../../../components/article-card/article-card.component';
import { FragranceCardComponent } from '../../../components/fragrance-card/fragrance-card.component';
import { ReviewCardComponent }    from '../../../components/review-card/review-card.component';

import { ArticleService }    from '../../../controller/article.service';
import { FragranceService }  from '../../../controller/fragrance.service';
import { ReviewService }     from '../../../controller/review.service';
import { SessionService }    from '../../../controller/session.service';
import { CollectionService } from '../../../controller/collection.service';

import { ArticleModel }      from '../../../model/article-model';
import { FragranceModel }    from '../../../model/fragrance-model';
import { FragranceReview }   from '../../../model/review-model';
import { LoggedUserProfile } from '../../../model/user-model';
import { FragranceCardSmallComponent } from "../../../components/fragrance-card-small/fragrance-card-small.component";
import { ArticleCardSmallComponent } from "../../../components/article-card-small/article-card-small.component";

@Component({
  standalone: true,
  selector: 'app-homepage',
  imports: [
    HeadlineComponent,
    ReviewCardComponent,
    NgxSpinnerComponent,
    NgIf,
    NgFor,
    FragranceCardSmallComponent,
    ArticleCardSmallComponent
],
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css'],
  host: { class: 'max-body-width m-auto w-full flex flex-col mt-5' }
})
export class HomepageComponent implements OnInit {
  recentArticles:   ArticleModel[]    = [];
  randomFragrances: FragranceModel[]  = [];
  recentReviews:    FragranceReview[] = [];
  userCollections:  FragranceModel[]  = [];
  currentUser:      LoggedUserProfile | null = null;

  constructor(
    private articleService:    ArticleService,
    private fragranceService:  FragranceService,
    private reviewService:     ReviewService,
    private session:           SessionService,
    private collectionService: CollectionService,
    private spinner:           NgxSpinnerService
  ) {}

  ngOnInit(): void {
    this.spinner.show();

    // 1) Load most recent articles
    this.articleService.getRecentArticles().subscribe(
      (list: ArticleModel[]) => {
        this.recentArticles = list;
      },
      () => {
        this.spinner.hide();
      },
      () => {
        this.spinner.hide();
      }
    );

    // 2) Load random fragrances
    this.fragranceService.getRandomFragrances(8).subscribe(
      (list: FragranceModel[]) => {
        this.randomFragrances = list;
      }
    );

    this.reviewService.getRecentFragranceReviews(5).subscribe(
      (list: FragranceReview[]) => {
        this.recentReviews = list;
      }
    );

    this.session.fetchUserProfile().subscribe(
      (user: LoggedUserProfile) => {
        this.currentUser = user;
        this.collectionService.getWholeCollection().subscribe(
          (colls: FragranceModel[]) => {
            this.userCollections = colls.slice(0, 2);
          },
          (err: any) => {}
        );
      },
      (err: any) => {}
    );
  }
}

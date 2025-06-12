import { Component, OnInit } from '@angular/core';
import { NgFor } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup } from '@angular/forms';
import { NgxSpinnerComponent, NgxSpinnerService } from 'ngx-spinner';
import { Router } from '@angular/router';

import { HeadlineComponent } from '../../../components/headline/headline.component';
import { SearchBarComponent } from '../../../components/search-bar/search-bar.component';
import { ArticleCardComponent } from '../../../components/article-card/article-card.component';

import { ArticleService } from '../../../controller/article.service';
import { ArticleModel } from '../../../model/article.model';

@Component({
  standalone: true,
  selector: 'app-browse-articles',
  imports: [
    HeadlineComponent,
    SearchBarComponent,
    ArticleCardComponent,
    NgxSpinnerComponent,
    NgFor,
    ReactiveFormsModule
  ],
  templateUrl: './browse-articles.component.html',
  styleUrls: ['./browse-articles.component.css'],
  host: {
    class: 'max-body-width mx-auto px-10 w-full flex flex-col mt-5'
  }
})
export class BrowseArticlesComponent implements OnInit {
  protected searchResult: ArticleModel[] = [];
  protected currentSearchTerm = '';
  protected currentPage = 0;
  protected pageSize = 6;
  protected lastPage = 1;
  protected filters: FormGroup;

  constructor(
    private articleService: ArticleService,
    private spinner: NgxSpinnerService,
    private fb: FormBuilder,
    private router: Router
  ) {
    // stub for future filters
    this.filters = this.fb.group({
      // e.g. 'category': [''], 'tag': ['']
    });
  }

  ngOnInit(): void {
    this.loadArticles();
  }

  private loadArticles(): void {
    this.spinner.show();
    this.articleService
      .getAllArticles(this.currentSearchTerm, this.currentPage, this.pageSize)
      .subscribe({
        next: (resp: any) => {
          this.searchResult = resp.content as ArticleModel[];
          this.lastPage = resp.totalPages;
          this.spinner.hide();
        },
        error: () => this.spinner.hide(),
      });
  }

  getArticlesFiltered(searchTerm: string): void {
    this.currentSearchTerm = searchTerm;
    this.currentPage = 0;
    this.loadArticles();
  }

  onNextClick(): void {
    if (this.currentPage + 1 < this.lastPage) {
      this.currentPage++;
      this.loadArticles();
    }
  }

  onPrevClick(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.loadArticles();
    }
  }

  navigateToArticleOverview(id: number | string): void {
    this.router.navigate(['/articles', id]);
  }
}

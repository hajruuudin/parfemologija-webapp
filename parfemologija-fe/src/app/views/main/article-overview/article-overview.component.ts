import { Component, OnInit } from '@angular/core';
import { NgIf, NgFor, CurrencyPipe } from '@angular/common';
import { NgxSpinnerComponent, NgxSpinnerService } from 'ngx-spinner';
import { ActivatedRoute, Router } from '@angular/router';

import { ArticleService } from '../../../controller/article.service';
import { ArticleModel }   from '../../../model/article-model';
import { GenderPipe } from '../../../pipes/gender.pipe';
import { UserModel } from '../../../model/user-model';
import { UserService } from '../../../controller/user.service';
import { error } from 'console';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  standalone: true,
  selector: 'app-article-overview',
  imports: [
    GenderPipe,
    CurrencyPipe,
    NgxSpinnerComponent
  ],
  templateUrl: './article-overview.component.html',
  styleUrls: ['./article-overview.component.css'],
  host: {
    class: 'max-body-width w-full flex flex-col md:flex-row md:m-auto justify-center md:space-x-2 items-start'
  }
})
export class ArticleOverviewComponent implements OnInit {
  article: ArticleModel | null = null;
  articleUser: UserModel | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private articleService: ArticleService,
    private userService: UserService,
    private spinner: NgxSpinnerService
  ) {}

  ngOnInit(): void {
    this.spinner.show();
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.articleService.getArticleById(id).subscribe(
        (res: ArticleModel) => {
          this.article = res;

          this.userService.getUserById(this.article.userId).subscribe({
            next: (response : UserModel) => {
              this.articleUser = response
            },
            error: (error : HttpErrorResponse) => {}
          })
        },
        (err: any) => {
          console.error(err);
        },
        () => {
          this.spinner.hide();
        }
      );
    } else {
      this.spinner.hide();
    }
  }

  goBack(): void {
    this.router.navigate(['/articles']);
  }
}

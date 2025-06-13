import { animate, state, style, transition, trigger } from '@angular/animations';
import { NgFor, NgIf } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ChangeDetectorRef, Component } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink, RouterLinkActive, RouterModule } from '@angular/router';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs';
import { ArticleService } from '../../controller/article.service';
import { ArticleModel } from '../../model/article-model';

@Component({
  selector: 'app-navbar',
  imports: [NgIf, NgFor, RouterLink, RouterLinkActive, ReactiveFormsModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
  animations: [
    trigger('slideInOut', [
      state('in', style({ height: '*', opacity: 1})),
      state('out', style({ height: '0px', opacity: 0, display: 'hidden'})),
      transition('in => out', animate('150ms ease-in-out')),
      transition('out => in', animate('150ms ease-in-out')),
    ]),
  ],
})
export class NavbarComponent {
  isMobileNavbarDropdown : Boolean = false;
  isDropdownOpen = false;
  searchControl = new FormControl('');
  searchResults: ArticleModel[] = [];

  constructor(
    private http: HttpClient, 
    private router: Router,
    private articleService: ArticleService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.searchControl.valueChanges.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap(query =>
        this.articleService.getAllArticles(query || '', 0, 20)
      )
    ).subscribe(response => {
      this.searchResults = response.content;
      this.isDropdownOpen = this.searchResults.length > 0;
    });
  }


  goToArticle(articleId: number) {
    this.router.navigate([`/articles/${articleId}`]).then(() => {
      this.searchControl.setValue('');
      this.searchResults = [];
      this.isDropdownOpen = false;
      this.cdr.detectChanges(); // Force update
    });
  }

  toggleMobileNavbarDropdown(){
    this.isMobileNavbarDropdown = !this.isMobileNavbarDropdown;
  }

  get slideInOutState(): string {
    return this.isMobileNavbarDropdown ? 'in' : 'out';
  }

  hideDropdownLater() {
    setTimeout(() => this.isDropdownOpen = false, 200);
  }

}

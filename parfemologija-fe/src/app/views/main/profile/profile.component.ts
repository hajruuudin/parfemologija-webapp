import { Component, OnInit } from '@angular/core';
import { LoggedUserProfile } from '../../../model/user-model';
import { SessionService } from '../../../controller/session.service';
import { NgFor, NgIf } from '@angular/common';
import { Router } from '@angular/router';
import { NgxSpinnerModule, NgxSpinnerService } from 'ngx-spinner';
import { FragranceModel } from '../../../model/fragrance-model';
import { WishlistService } from '../../../controller/wishlist.service';
import { CollectionService } from '../../../controller/collection.service';
import { response } from 'express';
import { HttpErrorResponse } from '@angular/common/http';
import { FragranceCardSmallComponent } from "../../../components/fragrance-card-small/fragrance-card-small.component";
import { ArticleService } from '../../../controller/article.service';
import { ArticleModel } from '../../../model/article-model';
import { ArticleCardComponent } from "../../../components/article-card/article-card.component";
import { ArticleCardSmallComponent } from "../../../components/article-card-small/article-card-small.component";
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../../../controller/auth.service';

@Component({
  selector: 'app-profile',
  imports: [NgFor, NgIf, NgxSpinnerModule, FragranceCardSmallComponent, ArticleCardSmallComponent],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
  host: {
    class: 'max-body-width w-full m-auto flex flex-col'
  }
})
export class ProfileComponent implements OnInit{
  protected currentUser : LoggedUserProfile | null = null;
  protected userWishlist : FragranceModel[] = []
  protected userCollection : FragranceModel[] = []
  protected userArticles : ArticleModel[] = []

  constructor(
    private sessionService: SessionService,
    private wishlistService: WishlistService,
    private collectionService: CollectionService,
    private articleService: ArticleService,
    private authService: AuthService,
    private spinner: NgxSpinnerService,
    private toastr: ToastrService,
    private router: Router
  ){ }

  ngOnInit(): void {
    this.spinner.show()

    this.sessionService.fetchUserProfile().subscribe({
      next: (response : any) => {
        this.spinner.hide()
        this.currentUser = response as LoggedUserProfile
      },
      error: (error : Error) => {
        this.spinner.hide()
      }
    })

    this.fetchWishlist()
    this.fetchCollection()
    this.fetchArticles()
  }

  deleteArticle(articelId: number){
    this.spinner.show()

    this.articleService.deleteArticle(articelId).subscribe({
      next: (response : any) => {
        this.spinner.hide()
        this.toastr.success("Article deleted")
        this.fetchArticles()
      }
    })
  }

  fetchWishlist(){
    this.wishlistService.getWholeWishlist().subscribe({
      next: (response : any) => {
        this.userWishlist = response
      }
    })
  }

  fetchCollection(){
    this.collectionService.getWholeCollection().subscribe({
      next: (response : any) => {
        this.userCollection = response
      }
    })
  }

  fetchArticles(){
    this.articleService.getUserArticles().subscribe({
      next: (response : ArticleModel[]) => {
        this.userArticles = response
      }
    })
  }

  navigateToAddFragrancePage(){
    this.router.navigate(['/fragrance/add'])
  }

  navigateToAddArticlePage(){
    this.router.navigate(['/article/add'])
  }

  navigateToFragranceOverview(fragranceSlug: string){
    this.router.navigate([`/fragrances/${fragranceSlug}`])
  }

  navigateToArticleOverview(articelId: number){
    this.router.navigate([`/articles/${articelId}`])
  }

  logout(){
    this.authService.logout().subscribe({
      next: (response: any) => {
        this.router.navigate(['auth/login'])
      }
    })
  }
}

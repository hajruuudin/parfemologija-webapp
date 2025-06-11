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

@Component({
  selector: 'app-profile',
  imports: [NgFor, NgIf, NgxSpinnerModule, FragranceCardSmallComponent],
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
  protected userArticles : {}[] = []

  constructor(
    private sessionService: SessionService,
    private wishlistService: WishlistService,
    private collectionService: CollectionService,
    private spinner: NgxSpinnerService,
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

    this.wishlistService.getWholeWishlist().subscribe({
      next: (response : any) => {
        this.userWishlist = response
      },
      error: (error : HttpErrorResponse) => { }
    })

    this.collectionService.getWholeCollection().subscribe({
      next: (response : any) => {
        this.userCollection = response
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
}

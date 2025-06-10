import { Component, OnInit, Type } from '@angular/core';
import { FragranceModel } from '../../../model/fragrance-model';
import { ActivatedRoute, Router } from '@angular/router';
import { FragranceService } from '../../../controller/fragrance.service';
import { Brand } from '../../../model/brand-model';
import { BrandService } from '../../../controller/brand.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgxSpinner, NgxSpinnerComponent, NgxSpinnerService } from 'ngx-spinner';
import { FragranceReview, FragranceReviewCreate } from '../../../model/review-model';
import { DatePipe, NgClass, NgFor, NgIf, NgStyle } from '@angular/common';
import { TypePipe } from '../../../pipes/type.pipe';
import { SessionService } from '../../../controller/session.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ReviewService } from '../../../controller/review.service';
import { ReviewCardComponent } from "../../../components/review-card/review-card.component";
import { LoggedUserProfile } from '../../../model/user-model';


@Component({
  selector: 'app-fragrance-overview',
  imports: [NgIf, NgFor, NgStyle, NgxSpinnerComponent, TypePipe, ReactiveFormsModule, ReviewCardComponent],
  templateUrl: './fragrance-overview.component.html',
  styleUrl: './fragrance-overview.component.css',
  host: {
    class: 'max-body-width w-full flex flex-col md:flex-row md:m-auto justify-center md:space-x-2 items-start'
  }
})
export class FragranceOverviewComponent implements OnInit{
  protected fragrance: FragranceModel | null = null;
  protected fragranceBrand: Brand | null = null;
  protected fragranceReviews: FragranceReview[] = []
  protected currentUser : LoggedUserProfile | null = null;

  protected sillageRatingStyle = {};
  protected logevityRatingStyle = {};

  protected reviewForm : FormGroup;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private fragranceService: FragranceService,
    private brandService: BrandService,
    private session: SessionService,
    private review: ReviewService,
    private spinner : NgxSpinnerService,
    private toastr: ToastrService,
    private fb : FormBuilder
  ){
    this.reviewForm = this.fb.group({
      'head' : ['', Validators.required],
      'body' : ['', Validators.required],
      'rating' : ['5']
    })

    this.session.fetchUserProfile().subscribe({
      next: (response: any) => {
        this.currentUser = response
      }
    })
  }

  ngOnInit(): void {
    // this.session.fetchUserProfile()
    this.spinner.show()
    this.fragranceService.getBySlug(
      this.route.snapshot.paramMap.get('slug') ?? ''
    ).subscribe({
      next: (response: any) => {
        this.fragrance = response as FragranceModel
        console.log(this.fragrance)

        this.brandService.getBrandById(this.fragrance.brandId).subscribe({
          next: (response : any) => {
            this.spinner.hide()
            this.fragranceBrand = response as Brand
            this.sillageRatingStyle = {
              width: `${(this.fragrance?.sillageRating ?? 0) * 10}%`,
              backgroundColor: 'rgb(34 211 238)',
              height: '1rem',
              borderRadius: '1rem'
            };

            this.logevityRatingStyle = {
              width: `${(this.fragrance?.longevityRating ?? 0) * 10}%`,
              backgroundColor: 'rgb(248 113 113)',
              height: '1rem',
              borderRadius: '1rem'
            };
          },

          error: (error: HttpErrorResponse) => {
            this.spinner.hide()
          }
        })

        this.loadFragranceReviews()
      },
      error: (error: Error) => {
        this.spinner.hide()
      }
    })
  }

  loadFragranceReviews(){
    this.review.getReviewsForFragrance(this.fragrance!.id).subscribe({
        next: (response: any) => {
            this.fragranceReviews = response as FragranceReview[]
            console.log(this.fragranceReviews)
          },
          error: (error: HttpErrorResponse) => {}
        })
  }

  onAddReviewPressed(event: Event){
    event.preventDefault()

    if(!this.reviewForm.valid){
      this.toastr.error("Please fill in the form properly!");
    } else {
      this.spinner.show()

      this.review.addReviewForFragrance(
        new FragranceReviewCreate(
          this.reviewForm.get('head')?.value,
          this.reviewForm.get('body')?.value,
          this.reviewForm.get('rating')?.value,
          this.fragrance!.id
        )
      ).subscribe({
        next: (response : any) => {
          this.spinner.hide()
          this.toastr.success("Review added!")
          this.loadFragranceReviews()

        },
        error: (error: HttpErrorResponse) => {
          // skotat nesto
        }
      })
    }
  }

  onDeleteUserReviewPressed(reviewId: number){
    this.spinner.show()

    this.review.deleteReviewById(reviewId).subscribe({
      next: (response : any) => {
        this.spinner.hide()
        this.toastr.success("Review Deleted")
        this.loadFragranceReviews()
      },
      error: (error : any) => {
        this.spinner.hide()
        this.toastr.error("Error while deleting review!")
      }
    })
  }
}

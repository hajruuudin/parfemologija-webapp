import { Component, OnInit } from '@angular/core';
import { FragranceModel } from '../../../model/fragrance-model';
import { ActivatedRoute, Router } from '@angular/router';
import { FragranceService } from '../../../controller/fragrance.service';
import { Brand } from '../../../model/brand-model';
import { BrandService } from '../../../controller/brand.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgxSpinner, NgxSpinnerComponent, NgxSpinnerService } from 'ngx-spinner';
import { FragranceReview } from '../../../model/review-model';
import { DatePipe, NgIf } from '@angular/common';
import { TypePipe } from '../../../pipes/type.pipe';

@Component({
  selector: 'app-fragrance-overview',
  imports: [ NgxSpinnerComponent, TypePipe],
  templateUrl: './fragrance-overview.component.html',
  styleUrl: './fragrance-overview.component.css',
  host: {
    class: 'max-body-width w-full flex flex-row m-auto justify-center space-x-2 items-start'
  }
})
export class FragranceOverviewComponent implements OnInit{
  protected fragrance: FragranceModel | null = null;
  protected fragranceBrand: Brand | null = null;
  protected fragranceReviews: FragranceReview[] = []

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private fragranceService: FragranceService,
    private brandService: BrandService,
    private spinner : NgxSpinnerService
  ){}

  ngOnInit(): void {
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
          },
          error: (error: HttpErrorResponse) => {

          }
        })
      },
      error: (error: Error) => {
      }
    })
  }
}

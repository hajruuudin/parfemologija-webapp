import { Component, OnInit } from '@angular/core';
import { FragranceService } from '../../../controller/fragrance.service';
import { NgxSpinnerComponent, NgxSpinnerModule, NgxSpinnerService } from 'ngx-spinner';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Brand } from '../../../model/brand-model';
import { NgFor, NgIf } from '@angular/common';
import { BrandService } from '../../../controller/brand.service';
import { HttpErrorResponse } from '@angular/common/http';
import { FragranceCreatModel, FragranceModel } from '../../../model/fragrance-model';
import { MediaService } from '../../../controller/media.service';

@Component({
  selector: 'app-add-fragrance',
  imports: [NgFor, NgxSpinnerModule, ReactiveFormsModule],
  templateUrl: './add-fragrance.component.html',
  styleUrl: './add-fragrance.component.css',
  host: {
    class: 'max-width-full w-full flex flex-col justify-center items-center m-auto'
  }
})
export class AddFragranceComponent implements OnInit{
  protected fragranceForm : FormGroup;
  protected selectBrands : Brand[] = []
  protected selectGenders: {name: string, value: string}[] = [
    {
      name: "All genders",
      value: ''
    },
    {
      name: 'For Him',
      value: 'for-him'
    },
    {
      name: 'For Her',
      value: 'for-her'
    },
    {
      name: 'Unisex',
      value: 'unisex'
    }
  ]
  protected selectTypes: { name: string, value: number }[] = [
    {
      name: 'All Types',
      value: 0
    },
    {
      name: 'Eau de Parfum',
      value: 1
    },
    {
      name: 'Eau de Toilette',
      value: 2
    },
    {
      name: 'Parfum',
      value: 3
    },
    {
      name: 'Eau de Cologne',
      value: 4
    },
    {
      name: 'Eau Fraîche',
      value: 5
    }
  ];
  protected selectedFile : File | null = null;

  constructor(
    private fragranceService: FragranceService,
    private spinner: NgxSpinnerService,
    private toastr: ToastrService,
    private brandService: BrandService,
    private mediaService: MediaService,
    private fb: FormBuilder
  ){
    this.fragranceForm = this.fb.group({
      'officialName' : ['', Validators.required],
      'brandId' : ['', Validators.required],
      'typeId' : ['', Validators.required],
      'winterRating' : ['', Validators.required],
      'fallRating' : ['', Validators.required],
      'summerRating' : ['', Validators.required],
      'springRating' : ['', Validators.required],
      'sillageRating' : ['', Validators.required],
      'longevityRating' : ['', Validators.required],
      'priceRange' : ['', Validators.required],
      'gender' : ['', Validators.required]
    })
  }

  ngOnInit(): void {
    this.brandService.getAllBrands().subscribe({
      next: (response : any) => {
        this.selectBrands = response['content']
      },
      error: (error : HttpErrorResponse) => {}
    })
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
      console.log('Selected file is:', this.selectedFile);
    }
  }

  async onFormSubmit(){
    if(!this.fragranceForm.valid){
      this.toastr.error("Please fill in the form!")
    } else {
      this.spinner.show();

      let imageUrl: string | null = null;

      if (this.selectedFile) {
        imageUrl = await this.mediaService.uploadImage(this.selectedFile);
        console.log('Uploaded image URL:', imageUrl);
      }

      const newFragrance = new FragranceCreatModel(
        this.fragranceForm.get('officialName')?.value,
        this.fragranceForm.get('brandId')?.value,
        this.fragranceForm.get('typeId')?.value,
        this.fragranceForm.get('winterRating')?.value,
        this.fragranceForm.get('fallRating')?.value,
        this.fragranceForm.get('summerRating')?.value,
        this.fragranceForm.get('springRating')?.value,
        this.fragranceForm.get('sillageRating')?.value,
        this.fragranceForm.get('longevityRating')?.value,
        this.fragranceForm.get('priceRange')?.value,
        this.fragranceForm.get('gender')?.value,
      )

      this.fragranceService.addNewFragrance(newFragrance).subscribe({
        next: (response : any) => {
          this.spinner.hide()
          this.toastr.success("Fragrance added!")

          if(imageUrl != null){
            this.mediaService.storeImageUrlToDatabase(response['id'], 'FRAGRANCE', imageUrl!).subscribe({
              next: (response : any) => {
                this.toastr.success("Image Added")
              },
              error: (response : HttpErrorResponse) => {
                this.toastr.error("Image not added")
              }
            })
          }
         
        },
        error: (error: HttpErrorResponse) => {
          this.spinner.hide()
          this.toastr.error("Error while adding fragrance")
        }
      })
    }
  }
}

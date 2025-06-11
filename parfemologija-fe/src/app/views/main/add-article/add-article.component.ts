import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MediaService } from '../../../controller/media.service';
import { BrandService } from '../../../controller/brand.service';
import { ToastrService } from 'ngx-toastr';
import { NgxSpinnerComponent, NgxSpinnerModule, NgxSpinnerService } from 'ngx-spinner';
import { FragranceService } from '../../../controller/fragrance.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ArticleCreateModel } from '../../../model/article-model';
import { NgFor } from '@angular/common';
import { ArticleService } from '../../../controller/article.service';

@Component({
  selector: 'app-add-article',
  imports: [NgFor, NgxSpinnerModule, ReactiveFormsModule],
  templateUrl: './add-article.component.html',
  styleUrl: './add-article.component.css',
  host: {
    class: 'max-width-full w-full flex flex-col justify-center items-center m-auto'
  }
})
export class AddArticleComponent {
  protected articleForm : FormGroup
  protected selectedFile : File | null = null;
  protected selectTypes: {name: string, value: string}[] = [
    {
      name: 'Original & New',
      value: 'original-new'
    },
    {
      name: 'Original & Used',
      value: 'original-used'
    },
    {
      name: 'Clone & New',
      value: 'clone-new'
    },
    {
      name: 'Clone & Used',
      value: 'clone-used'
    },
    {
      name: 'Decant',
      value: 'decant'
    }
  ]

  constructor(
    private spinner: NgxSpinnerService,
    private articleService: ArticleService,
    private toastr: ToastrService,
    private mediaService: MediaService,
    private fb: FormBuilder
  ){
    this.articleForm = this.fb.group({
      'articleTitle' : ['', Validators.required],
      'articleDescription' : ['', Validators.required],
      'articleType' : ['', Validators.required],
      'articlePrice' : ['', Validators.required]
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
    if(!this.articleForm.valid){
      this.toastr.error("Please fill in the form!")
    } else {
      this.spinner.show();

      let imageUrl: string | null = null;

      if (this.selectedFile) {
        imageUrl = await this.mediaService.uploadImage(this.selectedFile);
        console.log('Uploaded image URL:', imageUrl);
      }

      const newArticle = new ArticleCreateModel(
        this.articleForm.get('articleTitle')?.value,
        this.articleForm.get('articleDescription')?.value,
        this.articleForm.get('articleType')?.value,
        this.articleForm.get('articlePrice')?.value
      )

      this.articleService.addArticleToDatabase(newArticle).subscribe({
        next: (response : any) => {
          this.spinner.hide()
          this.toastr.success("Article added!")

          if(imageUrl != null){
            this.mediaService.storeImageUrlToDatabase(response['id'], 'ARTICLE', imageUrl!).subscribe({
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

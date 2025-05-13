import { Component, OnInit } from '@angular/core';
import { HeadlineComponent } from "../../../components/headline/headline.component";
import { FragranceModel } from '../../../model/fragrance-model';
import { FragranceService } from '../../../controller/fragrance.service';
import { NgxSpinnerComponent, NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-browse-articles',
  imports: [HeadlineComponent, NgxSpinnerComponent],
  templateUrl: './browse-articles.component.html',
  styleUrl: './browse-articles.component.css',
  host: {
    class: 'max-body-width m-auto w-full flex flex-col justify-center items-center mt-5'
  }
})
export class BrowseArticlesComponent {
  constructor(
      private fragranceService: FragranceService,
      private spinner: NgxSpinnerService
    ) {}
  
    ngOnInit(): void {
      this.spinner.show()
      this.fragranceService.getAllFragrances(0, 10).subscribe({
        next: (response: any) => {
          this.spinner.hide()
          console.log("This only runs if the user is authenticated")
        },
        error: (error: any) => {
          this.spinner.hide()
          console.log("This means the user is not authenticated")
        }
      })
    }
}

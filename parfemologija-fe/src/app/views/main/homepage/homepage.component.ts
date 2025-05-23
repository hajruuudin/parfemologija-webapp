import { Component, OnInit } from '@angular/core';
import { HeadlineComponent } from "../../../components/headline/headline.component";
import { FragranceService } from '../../../controller/fragrance.service';
import { NgxSpinner, NgxSpinnerComponent, NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-homepage',
  imports: [HeadlineComponent, NgxSpinnerComponent],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css',
  host: {
    class: 'max-body-width m-auto w-full flex flex-col justify-center items-center mt-5'
  }
})
export class HomepageComponent implements OnInit{
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

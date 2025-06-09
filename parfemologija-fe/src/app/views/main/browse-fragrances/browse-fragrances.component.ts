import { Component, OnInit } from '@angular/core';
import { HeadlineComponent } from "../../../components/headline/headline.component";
import { SearchBarComponent } from "../../../components/search-bar/search-bar.component";
import { FragranceModel } from '../../../model/fragrance-model';
import { FragranceService } from '../../../controller/fragrance.service';
import { FragranceCardComponent } from "../../../components/fragrance-card/fragrance-card.component";
import { NgFor } from '@angular/common';
import { NgxSpinnerComponent, NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-browse-fragrances',
  imports: [NgFor, HeadlineComponent, SearchBarComponent, FragranceCardComponent, NgxSpinnerComponent],
  templateUrl: './browse-fragrances.component.html',
  styleUrl: './browse-fragrances.component.css',
  host: {
    class: 'max-body-width mx-auto px-10 w-full flex flex-col justify-center items-center mt-5'
  }
})
export class BrowseFragrancesComponent implements OnInit{
  protected searchResult: FragranceModel[] = []

  constructor(
    private fragranceService: FragranceService,
    private spinner: NgxSpinnerService
  ){}

  ngOnInit(): void {
    this.spinner.show()
    this.fragranceService.getAllFragrances(0, 10).subscribe({
      next: (response: any) => {
        this.spinner.hide()
        this.searchResult = response['content'] as FragranceModel[]
        console.log(this.searchResult)
      },
      error: (error : Error) => {
        this.spinner.hide()
      }
    })
  }
}

import { Component, OnInit } from '@angular/core';
import { HeadlineComponent } from "../../../components/headline/headline.component";
import { SearchBarComponent } from "../../../components/search-bar/search-bar.component";
import { FragranceModel } from '../../../model/fragrance-model';
import { FragranceService } from '../../../controller/fragrance.service';

@Component({
  selector: 'app-browse-fragrances',
  imports: [HeadlineComponent, SearchBarComponent],
  templateUrl: './browse-fragrances.component.html',
  styleUrl: './browse-fragrances.component.css',
  host: {
    class: 'max-body-width mx-auto px-10 w-full flex flex-col justify-center items-center mt-5'
  }
})
export class BrowseFragrancesComponent implements OnInit{
searchResult: FragranceModel[] = []

  constructor(
    private fragranceService: FragranceService
  ){}

  ngOnInit(): void {
    this.fragranceService.getAllFragrances(0, 10).subscribe({
      next: (response: any) => {
        this.searchResult = response['content'] as FragranceModel[]
        console.log(this.searchResult)
      },
      error: (error : Error) => {
        console.log(error)
      }
    })
  }
}

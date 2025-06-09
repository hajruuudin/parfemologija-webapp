import { Component, OnInit } from '@angular/core';
import { HeadlineComponent } from "../../../components/headline/headline.component";
import { SearchBarComponent } from "../../../components/search-bar/search-bar.component";
import { FragranceModel } from '../../../model/fragrance-model';
import { FragranceService } from '../../../controller/fragrance.service';
import { FragranceCardComponent } from "../../../components/fragrance-card/fragrance-card.component";
import { NgFor } from '@angular/common';
import { NgxSpinnerComponent, NgxSpinnerService } from 'ngx-spinner';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { BrandService } from '../../../controller/brand.service';
import { Brand } from '../../../model/brand-model';
import { response } from 'express';
import { Router } from '@angular/router';

@Component({
  selector: 'app-browse-fragrances',
  imports: [NgFor, HeadlineComponent, SearchBarComponent, FragranceCardComponent, NgxSpinnerComponent, ReactiveFormsModule],
  templateUrl: './browse-fragrances.component.html',
  styleUrl: './browse-fragrances.component.css',
  host: {
    class: 'max-body-width mx-auto px-10 w-full flex flex-col justify-center items-center mt-5'
  }
})
export class BrowseFragrancesComponent implements OnInit{
  protected searchResult: FragranceModel[] = [];
  protected currentSearchTerm: string = '';
  protected filters: FormGroup;

  protected allBrands: Brand[] = [];
  protected allGenders: {name: string, value: string}[] = [
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
  protected allTypes: { name: string, value: number }[] = [
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

  protected currentPage = 0;
  protected pageSize = 6;
  protected lastPage = 999;

  constructor(
    private fragranceService: FragranceService,
    private spinner: NgxSpinnerService,
    private brandService: BrandService,
    private fb: FormBuilder,
    private router: Router
  ){
    this.filters = this.fb.group({
      'gender' : [''],
      'type' : ['0'],
      'brand' : [[]]
    })
  }

  ngOnInit(): void {
    this.spinner.show()

    this.brandService.getAllBrands().subscribe({
      next: (response: any) => {
        this.allBrands = response['content'] as Brand[]
      },
      error: (error: Error) => {
      }
    })

    this.fragranceService.getAllFragrances('', this.currentPage, this.pageSize, [], 0, '').subscribe({
      next: (response: any) => {
        this.spinner.hide()
        this.searchResult = response['content'] as FragranceModel[]
        this.lastPage = response['totalPages']
      },
      error: (error : Error) => {
        this.spinner.hide()
      }
    })
  }

  getFragrancesFiltered(searchTerm: string){
    this.spinner.show()
    this.currentPage = 0;
    this.currentSearchTerm = searchTerm;
    
    this.fragranceService.getAllFragrances(
      searchTerm,
       this.currentPage, 
       6,
        this.filters.get('brand')?.value,
        this.filters.get('type')?.value,
        this.filters.get('gender')?.value
      ).subscribe({
      next: (response: any) => {
        this.spinner.hide()
        this.searchResult = response['content'] as FragranceModel[]
        this.lastPage = response['totalPages']
      },
      error: (error : Error) => {
        this.spinner.hide()
      }
    })
  }

  onBrandCheckboxChange(event: any): void {
    const brandId = parseInt(event.target.value, 10);
    const isChecked = event.target.checked;
    const brandOptionsControl = this.filters.get('brand');
    let currentBrands = brandOptionsControl?.value as number[];

    if (isChecked) {
      currentBrands = [...currentBrands, brandId];
    } else {
      currentBrands = currentBrands.filter(id => id !== brandId);
    }

    brandOptionsControl?.setValue(currentBrands);
  }

  onNextClick(){
    if(this.currentPage + 1 == this.lastPage){
      return
    }
    this.currentPage++;
    this.spinner.show()

    this.fragranceService.getAllFragrances(
        this.currentSearchTerm,
        this.currentPage, 
        6,
        this.filters.get('brand')?.value,
        this.filters.get('type')?.value,
        this.filters.get('gender')?.value
      ).subscribe({
      next: (response: any) => {
        this.spinner.hide()
        this.searchResult = response['content'] as FragranceModel[]
      },
      error: (error : Error) => {
        this.spinner.hide()
      }
    })
  }

  onPrevClick(){
    if(this.currentPage == 0){
      return
    }
    this.currentPage--;
    this.spinner.show()

    this.fragranceService.getAllFragrances(
        this.currentSearchTerm,
        this.currentPage, 
        6,
        this.filters.get('brand')?.value,
        this.filters.get('type')?.value,
        this.filters.get('gender')?.value
      ).subscribe({
      next: (response: any) => {
        this.spinner.hide()
        this.searchResult = response['content'] as FragranceModel[]
      },
      error: (error : Error) => {
        this.spinner.hide()
      }
    })
  }

  navigateToFragranceOverview(slug: string){
    this.router.navigate(['/fragrances', slug])
  }
}

import { Component, OnInit } from '@angular/core';
import { LoggedUserProfile } from '../../../model/user-model';
import { SessionService } from '../../../controller/session.service';
import { NgIf } from '@angular/common';
import { Router } from '@angular/router';
import { NgxSpinnerModule, NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-profile',
  imports: [NgIf, NgxSpinnerModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
  host: {
    class: 'max-body-width w-full m-auto flex flex-col'
  }
})
export class ProfileComponent implements OnInit{
  protected currentUser : LoggedUserProfile | null = null;

  constructor(
    private sessionService : SessionService,
    private spinner: NgxSpinnerService,
    private router: Router
  ){
    
  }

  ngOnInit(): void {
    this.spinner.show()
    this.sessionService.fetchUserProfile().subscribe({
      next: (response : any) => {
        this.spinner.hide()
        this.currentUser = response as LoggedUserProfile
      },
      error: (error : Error) => {}
    })
  }

  navigateToAddFragrancePage(){
    this.router.navigate(['/fragrance/add'])
  }

  navigateToAddArticlePage(){
    this.router.navigate(['/article/add'])
  }
}

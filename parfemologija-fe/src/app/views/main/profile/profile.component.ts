import { Component, OnInit } from '@angular/core';
import { LoggedUserProfile } from '../../../model/user-model';
import { SessionService } from '../../../controller/session.service';
import { NgIf } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  imports: [NgIf],
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
    private router: Router
  ){
    this.sessionService.fetchUserProfile().subscribe({
      next: (response : any) => {
        this.currentUser = response as LoggedUserProfile
      },
      error: (error : Error) => {}
    })
  }

  ngOnInit(): void {
    
  }

  navigateToAddFragrancePage(){
    this.router.navigate(['/fragrance/add'])
  }
}

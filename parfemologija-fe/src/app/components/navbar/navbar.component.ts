import { animate, state, style, transition, trigger } from '@angular/animations';
import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [NgIf, RouterLink, RouterLinkActive],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
  animations: [
    trigger('slideInOut', [
      state('in', style({ height: '*', opacity: 1})),
      state('out', style({ height: '0px', opacity: 0, display: 'hidden'})),
      transition('in => out', animate('150ms ease-in-out')),
      transition('out => in', animate('150ms ease-in-out')),
    ]),
  ],
})
export class NavbarComponent {
  isMobileNavbarDropdown : Boolean = false;

  toggleMobileNavbarDropdown(){
    this.isMobileNavbarDropdown = !this.isMobileNavbarDropdown;
  }

  get slideInOutState(): string {
    return this.isMobileNavbarDropdown ? 'in' : 'out';
  }

}

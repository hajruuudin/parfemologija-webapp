import { Component } from '@angular/core';
import { NavbarComponent } from "../../../components/navbar/navbar.component";
import { RouterOutlet } from '@angular/router';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-main-container',
  imports: [RouterOutlet, NavbarComponent],
  templateUrl: './main-container.component.html',
  styleUrl: './main-container.component.css',
  host: {
    class: 'w-full h-screen flex-col justify-start items-center relative'
  }
})
export class MainContainerComponent {

}

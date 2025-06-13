import { Component } from '@angular/core';
import { NavbarComponent } from "../../../components/navbar/navbar.component";
import { RouterOutlet } from '@angular/router';
import { NgIf } from '@angular/common';
import { FooterComponent } from "../../../components/footer/footer.component";

@Component({
  selector: 'app-main-container',
  imports: [RouterOutlet, NavbarComponent, FooterComponent],
  templateUrl: './main-container.component.html',
  styleUrl: './main-container.component.css',
  host: {
    class: 'w-full h-full flex flex-col justify-between items-stretch relative'
  }
})
export class MainContainerComponent {

}

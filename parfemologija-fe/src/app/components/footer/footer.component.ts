import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  imports: [],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css',
  host: {
    class: 'py-4 bg-(--secondary-200) h-64 w-full flex flex-col justify-start items-center'
  }
})
export class FooterComponent {

}

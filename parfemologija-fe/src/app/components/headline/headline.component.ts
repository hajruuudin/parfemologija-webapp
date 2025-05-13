import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-headline',
  imports: [],
  templateUrl: './headline.component.html',
  styleUrl: './headline.component.css',
  host: {
    class: 'w-full'
  }
})
export class HeadlineComponent {
  @Input() title : string = "";
}

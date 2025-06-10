import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FragranceReview } from '../../model/review-model';
import { DatePipe, NgIf } from '@angular/common';

@Component({
  selector: 'app-review-card',
  imports: [NgIf, DatePipe],
  templateUrl: './review-card.component.html',
  styleUrl: './review-card.component.css',
  host: {
    class: 'w-full h-auto px-2 py-4 my-2 rounded-xl border-2 bg-(--secondary-300) border-(--secondary-500) flex flex-col justify-start items-stretch'
  }
})
export class ReviewCardComponent {
  @Input() review : FragranceReview | null = null;
  @Input() isUserReview : boolean = false;
  @Output() deletePressed = new EventEmitter<number>();

  deleteReview(event: Event){
    this.deletePressed.emit(this.review?.id)
  }
}

import { Component, Input } from '@angular/core';
import { CurrencyPipe, NgIf } from '@angular/common';
import { ArticleModel } from '../../model/article-model';

@Component({
  standalone: true,
  selector: 'app-article-card',
  imports: [CurrencyPipe],
  templateUrl: './article-card.component.html',
  styleUrls: ['./article-card.component.css'],
  host: {
    class: 'w-full sm:w-1/2 lg:w-1/3 h-auto flex flex-col bg-(--primary-200) rounded-2xl shadow p-4 scale-95 cursor-pointer hover:shadow-lg transition'
  }
})
export class ArticleCardComponent {
  @Input() article!: ArticleModel;
}

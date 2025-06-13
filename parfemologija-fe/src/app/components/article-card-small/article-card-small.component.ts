import { Component, Input } from '@angular/core';
import { ArticleModel } from '../../model/article-model';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-article-card-small',
  imports: [CurrencyPipe],
  templateUrl: './article-card-small.component.html',
  styleUrl: './article-card-small.component.css',
  host: {
    class: 'min-w-80 h-72 flex flex-col bg-(--primary-200) rounded-2xl shadow p-4 m-2 cursor-pointer hover:shadow-lg transition'
  }
})
export class ArticleCardSmallComponent {
  @Input() article : ArticleModel | null = null;
}

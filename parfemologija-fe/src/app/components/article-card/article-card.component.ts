import { Component, Input } from '@angular/core';
import { NgIf } from '@angular/common';
import { ArticleModel } from '../../model/article-model';

@Component({
  standalone: true,
  selector: 'app-article-card',
  imports: [NgIf],
  templateUrl: './article-card.component.html',
  styleUrls: ['./article-card.component.css'],
  host: {
    class: 'max-w-xs flex flex-col bg-white rounded-2xl shadow p-4 m-2 cursor-pointer hover:shadow-lg transition'
  }
})
export class ArticleCardComponent {
  @Input() article!: ArticleModel;
}

import { Component, Input } from '@angular/core';
import { FragranceModel } from '../../model/fragrance-model';
import { GenderPipe } from '../../pipes/gender.pipe';
import { TypePipe } from '../../pipes/type.pipe';

@Component({
  selector: 'app-fragrance-card',
  imports: [GenderPipe, TypePipe],
  templateUrl: './fragrance-card.component.html',
  styleUrl: './fragrance-card.component.css',
  host: {
    class: 'w-full sm:w-1/2 lg:w-1/3 h-auto flex flex-col justify-start items-center bg-(--secondary-300) hover:bg-(--primary-200) scale-95 rounded-xl outline-(--secondary-500) outline-2 hover:outline-4 transition-all group'
  }
})
export class FragranceCardComponent {
  @Input() fragrance : FragranceModel | null = null;
}

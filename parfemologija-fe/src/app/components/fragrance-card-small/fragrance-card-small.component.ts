import { Component, Input } from '@angular/core';
import { FragranceModel } from '../../model/fragrance-model';
import { TypePipe } from '../../pipes/type.pipe';
import { GenderPipe } from '../../pipes/gender.pipe';

@Component({
  selector: 'app-fragrance-card-small',
  imports: [TypePipe, GenderPipe],
  templateUrl: './fragrance-card-small.component.html',
  styleUrl: './fragrance-card-small.component.css',
  host: {
    class: 'min-w-72 min-h-80 h-auto flex flex-col justify-start items-center bg-(--secondary-300) hover:bg-(--primary-200) scale-95 rounded-xl outline-(--secondary-500) outline-2 hover:outline-4 transition-all group'
  }
})
export class FragranceCardSmallComponent {
  @Input() fragrance : FragranceModel | null = null;
}

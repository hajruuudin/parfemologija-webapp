import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'type'
})
export class TypePipe implements PipeTransform {
  private perfumeMap: { [key: number]: string } = {
    1: 'Eau de Parfum',
    2: 'Eau de Toilette',
    3: 'Parfum',
    4: 'Eau de Cologne',
    5: 'Eau Fraîche'
  };

  transform(value: number): string {
    return this.perfumeMap[value] || 'Unknown';
  }
}

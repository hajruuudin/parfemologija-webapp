import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'gender'
})
export class GenderPipe implements PipeTransform {

  transform(value: string | null | undefined): string | null {
    if (value === null || value === undefined) {
      return null;
    }
    if (typeof value !== 'string') {
      console.warn('HyphenToTitleCasePipe received a non-string value:', value);
      return String(value);
    }

    const spacedString = value.replace(/-/g, ' ');

    return spacedString.split(' ').map(word => {
      if (!word) {
        return '';
      }
      return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
    }).join(' ');
  }

}

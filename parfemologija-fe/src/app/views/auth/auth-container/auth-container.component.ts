import { Component, ElementRef, HostListener, ViewChild } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-auth-container',
  imports: [RouterOutlet],
  templateUrl: './auth-container.component.html',
  styleUrl: './auth-container.component.css',
  host: {
    class: 'w-full h-full flex justify-center items-center'
  }
})
export class AuthContainerComponent {
  @ViewChild('backgroundImage') backgroundImage : ElementRef | null = null;

  @HostListener('mousemove', ['$event'])
  onMouseMove(event: MouseEvent) {
    if (this.backgroundImage) {
      const x = event.clientX;
      const y = event.clientY;
      const speed = 0.01;

      const moveX = (x - window.innerWidth / 2) * speed;
      const moveY = (y - window.innerHeight / 2) * speed;

      this.backgroundImage.nativeElement.style.transform = `translate(${moveX}px, ${moveY}px)`;
    }
  }
}

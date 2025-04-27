import { animate, style, transition, trigger } from '@angular/animations';
import { Component, ElementRef, HostListener, ViewChild } from '@angular/core';
import { ActivationEnd, Router, RouterLink, RouterOutlet } from '@angular/router';
import { filter, map } from 'rxjs';

@Component({
  selector: 'app-auth-container',
  imports: [RouterOutlet],
  templateUrl: './auth-container.component.html',
  styleUrl: './auth-container.component.css',
  host: {
    class: 'w-full h-full flex justify-center items-center overflow-hidden'
  }
})
export class AuthContainerComponent {

  // Ne radi djavo:
  @ViewChild('backgroundImage') backgroundImage : ElementRef | null = null;

  @HostListener('mousemove', ['$event'])
  onMouseMove(event: MouseEvent) {
    if (this.backgroundImage) {
      const x = event.clientX;
      const y = event.clientY;
      const speed = 0.02;

      const moveX = (x - window.innerWidth / 2) * speed;
      const moveY = (y - window.innerHeight / 2) * speed;

      this.backgroundImage.nativeElement.style.transform = `translate(${moveX}px, ${moveY}px)`;
    }
  }
}

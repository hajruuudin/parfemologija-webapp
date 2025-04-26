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
    class: 'w-full h-full flex justify-center items-center'
  },
  animations: [ // Ni ovo ne radi
    trigger('routeAnimations', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('300ms ease-in', style({ opacity: 1 }))
      ]),
      transition(':leave', [
        animate('300ms ease-out', style({ opacity: 0 }))
      ])
    ])
  ]
})
export class AuthContainerComponent {
  animationState: string = '';

  constructor(private router: Router) {
    this.router.events.pipe(
      filter(event => event instanceof ActivationEnd),
      map(event => (event as ActivationEnd).snapshot.data['animation'])
    ).subscribe(data => {
      this.animationState = data || '';
    });
  }

  prepareRoute(outlet: RouterOutlet) {
    return this.animationState; // Directly return the component's state
  }
  // Ne radi djavo:
  // @ViewChild('backgroundImage') backgroundImage : ElementRef | null = null;

  // @HostListener('mousemove', ['$event'])
  // onMouseMove(event: MouseEvent) {
  //   if (this.backgroundImage) {
  //     const x = event.clientX;
  //     const y = event.clientY;
  //     const speed = 0.01;

  //     const moveX = (x - window.innerWidth / 2) * speed;
  //     const moveY = (y - window.innerHeight / 2) * speed;

  //     this.backgroundImage.nativeElement.style.transform = `translate(${moveX}px, ${moveY}px)`;
  //   }
  // }
}

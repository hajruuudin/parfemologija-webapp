import { ApplicationConfig, importProvidersFrom, inject, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideToastr } from 'ngx-toastr';

import { routes } from './app.routes';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import {  HttpHandlerFn, provideHttpClient, withInterceptors } from '@angular/common/http';
import { AuthInterceptorService } from './controller/auth-interceptor.service';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async'
import { provideSpinnerConfig, NgxSpinnerModule } from 'ngx-spinner'

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }), 
    provideRouter(routes), provideClientHydration(withEventReplay()),
    provideHttpClient(withInterceptors([
      (req, next: HttpHandlerFn) => inject(AuthInterceptorService).intercept(req, {
        handle: (internalReq) => next(internalReq)
      })
    ])),
    provideAnimationsAsync(),
    provideToastr(),
    importProvidersFrom(NgxSpinnerModule.forRoot())
  ]
};

import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Router } from 'express';
import { Observable, tap } from 'rxjs';

/**
 * 
 * This service is called upon each request and intercepts it checking for authentication.
 * If a request is authorised, the backend will return a normal HTTP Response.
 * If a request is not authorised, the backend will return a HTTP Error response with a status of 401.
 * The frontend will redirect to the login page upon a 401 unathorised request.
 * 
 */
@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor{

  private router = inject(Router)

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      tap(
        (event: HttpEvent<any>) => {
          if (event instanceof HttpResponse){
            // In this case, normal application flow is allowed
          }
        },
        (error: any) => {
          if (error instanceof HttpErrorResponse){
            if (error.status === 401){
              this.router.redirect(['/auth'])
            }
          }
        }
      )
    )
  }
}

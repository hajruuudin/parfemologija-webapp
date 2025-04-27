import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserCreate, UserLogin } from '../model/user-model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http : HttpClient) { }

  register(request : UserCreate){
    return this.http.post('/api/auth/register', request, { /* No Options */})
  }

  login(request : UserLogin){
    return this.http.post('/api/auth/login', request, { /* No Options */ })
  }

  checkUsername(request : String){
    return this.http.get(`/api/auth/check/${request}`)
  }
}

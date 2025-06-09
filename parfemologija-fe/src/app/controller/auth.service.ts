import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserCreate, UserLogin } from '../model/user-model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = environment.apiUrl

  constructor(private http : HttpClient) { }

  register(request : UserCreate){
    console.log(this.apiUrl)
    return this.http.post(`${this.apiUrl}/auth/register`, request, {
      withCredentials: true
    })
  }

  login(request : UserLogin){
    console.log(this.apiUrl)
    return this.http.post(`${this.apiUrl}/auth/login`, request, {
      withCredentials: true
    })
  }

  checkUsername(request : String){
    console.log(this.apiUrl)
    return this.http.get(`${this.apiUrl}/auth/check/${request}`, {
      withCredentials: true
    })
  }
}

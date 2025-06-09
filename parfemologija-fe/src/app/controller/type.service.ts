import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TypeService {
  private apiUrl = environment.apiUrl

  constructor(private http: HttpClient) {}

  getAllTypes(){
    return this.http.get(`${this.apiUrl}/type`, {
      withCredentials: true
    })
  }
}

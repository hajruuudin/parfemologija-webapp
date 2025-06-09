import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BrandService {
  private apiUrl = environment.apiUrl

  constructor(private http: HttpClient) {}

  getAllBrands(){
    return this.http.get(`${this.apiUrl}/brand`, {
      withCredentials : true}
    )
  }

  getBrandById(id: number){
    return this.http.get(`${this.apiUrl}/brand/${id}`, {
      withCredentials: true
    })
  }
}

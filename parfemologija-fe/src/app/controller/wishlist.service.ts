import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  checkWishlistStatus(fragranceSlug: string){
    return this.http.get(`${this.apiUrl}/wishlist/${fragranceSlug}`, {
      withCredentials: true
    })
  }

  addToWishlist(fraganceSlug: string){
    return this.http.post(`${this.apiUrl}/wishlist`, fraganceSlug, {
      withCredentials: true
    })
  }

  removeFromWishlist(fraganceSlug: string){
    return this.http.delete(`${this.apiUrl}/wishlist/${fraganceSlug}`, {
      withCredentials: true
    })
  }

  getWholeWishlist(){
    return this.http.get(`${this.apiUrl}/wishlist`, {
      withCredentials: true
    })
  }
}

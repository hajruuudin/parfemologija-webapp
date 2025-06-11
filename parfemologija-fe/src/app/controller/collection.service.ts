import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CollectionService {
  private apiUrl = environment.apiUrl;

  constructor(private http : HttpClient) { }

  checkCollectionStatus(fragranceSlug: string){
    return this.http.get(`${this.apiUrl}/collection/${fragranceSlug}`, {
      withCredentials: true
    })
  }

  addToCollection(fraganceSlug: string){
    return this.http.post(`${this.apiUrl}/collection`, fraganceSlug, {
      withCredentials: true
    })
  }

  removeFromCollection(fraganceSlug: string){
    return this.http.delete(`${this.apiUrl}/collection/${fraganceSlug}`, {
      withCredentials: true
    })
  }

  getWholeCollection(){
    return this.http.get(`${this.apiUrl}/collection`, {
      withCredentials: true
    })
  }
}

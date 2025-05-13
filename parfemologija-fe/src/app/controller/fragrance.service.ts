import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FragranceService {

  constructor(private http: HttpClient) {}

  getAllFragrances(pageNumber: number, pageSize: number){
    return this.http.get(`/api/fragrance/${pageNumber}/${pageSize}`, {
      withCredentials: true
    })
  }
}

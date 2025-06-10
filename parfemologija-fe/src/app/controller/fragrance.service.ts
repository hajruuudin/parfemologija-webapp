import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { FragranceCreatModel } from '../model/fragrance-model';

@Injectable({
  providedIn: 'root'
})
export class FragranceService {
  private apiUrl = environment.apiUrl

  constructor(private http: HttpClient) {}

  getAllFragrances(search: string, pageNumber: number, pageSize: number, brands: number[], type: number, gender: string){
    return this.http.get(`${this.apiUrl}/fragrance?search=${search}&pageNumber=${pageNumber}&pageSize=${pageSize}&brands=${brands}&type=${type}&gender=${gender}`, {
      withCredentials: true
    })
  }

  getBySlug(slug: string){
    return this.http.get(`${this.apiUrl}/fragrance/${slug}`, {
      withCredentials: true
    })
  }

  addNewFragrance(newFragrance: FragranceCreatModel){
    return this.http.post(`${this.apiUrl}/fragrance`, newFragrance, {
      withCredentials: true
    })
  }
}

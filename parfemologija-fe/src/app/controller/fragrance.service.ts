// src/app/controller/fragrance.service.ts

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { FragranceCreatModel, FragranceModel } from '../model/fragrance-model';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FragranceService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  /**
   * Fetch a paged list of fragrances with optional filters
   */
  getAllFragrances(
    search: string,
    pageNumber: number,
    pageSize: number,
    brands: number[],
    type: number,
    gender: string
  ) {
    const params = [
      `search=${encodeURIComponent(search)}`,
      `pageNumber=${pageNumber}`,
      `pageSize=${pageSize}`,
      `brands=${brands.join(',')}`,
      `type=${type}`,
      `gender=${encodeURIComponent(gender)}`
    ].join('&');

    return this.http.get<{
      content: FragranceModel[];
      totalPages: number;
    }>(`${this.apiUrl}/fragrance?${params}`, {
      withCredentials: true
    });
  }

  /**
   * Fetch a single fragrance by slug
   */
  getBySlug(slug: string): Observable<FragranceModel> {
    return this.http.get<FragranceModel>(`${this.apiUrl}/fragrance/${slug}`, {
      withCredentials: true
    });
  }

  /**
   * Create a new fragrance
   */
  addNewFragrance(newFragrance: FragranceCreatModel): Observable<FragranceModel> {
    return this.http.post<FragranceModel>(
      `${this.apiUrl}/fragrance`,
      newFragrance,
      { withCredentials: true }
    );
  }

  /**
   * Return an array of FragranceModel for “random” picks
   */
  getRandomFragrances(count: number): Observable<FragranceModel[]> {
    return this.getAllFragrances('', 0, count, [], 0, '')
      .pipe(map(resp => resp.content));
  }
}

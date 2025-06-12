// src/app/controller/collection.service.ts

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { FragranceModel } from '../model/fragrance-model';

@Injectable({
  providedIn: 'root'
})
export class CollectionService {
  private apiUrl = environment.apiUrl;

  constructor(private http : HttpClient) { }

  /** Check if a single fragrance is in the user’s collection */
  checkCollectionStatus(slug: string): Observable<boolean> {
    return this.http.get<boolean>(
      `${this.apiUrl}/collection/${slug}`,
      { withCredentials: true }
    );
  }

  /** Add a fragrance to the user’s collection */
  addToCollection(slug: string): Observable<void> {
    return this.http.post<void>(
      `${this.apiUrl}/collection`,
      slug,
      { withCredentials: true }
    );
  }

  /** Remove a fragrance from the user’s collection */
  removeFromCollection(slug: string): Observable<void> {
    return this.http.delete<void>(
      `${this.apiUrl}/collection/${slug}`,
      { withCredentials: true }
    );
  }

  /**
   * Fetch the user’s entire collection as an array of FragranceModel.
   * Now typed so you can call .slice() on the result.
   */
  getWholeCollection(): Observable<FragranceModel[]> {
    return this.http.get<FragranceModel[]>(
      `${this.apiUrl}/collection`,
      { withCredentials: true }
    );
  }
}

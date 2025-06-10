import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FragranceReviewCreate } from '../model/review-model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  addReviewForFragrance(review: FragranceReviewCreate){
    return this.http.post(`${this.apiUrl}/review`, review, {
      withCredentials: true
    })
  }

  getReviewsForFragrance(fragranceId: number){
    return this.http.get(`${this.apiUrl}/review?fragranceId=${fragranceId}`, {
      withCredentials : true
    })
  }

  deleteReviewById(reviewId: number){
    return this.http.delete(`${this.apiUrl}/review?reviewId=${reviewId}`, {
      withCredentials: true
    })
  }
}

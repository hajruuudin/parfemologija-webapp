import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FragranceReviewCreate, FragranceReview } from '../model/review-model';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  /** Existing methods… */
  addReviewForFragrance(review: FragranceReviewCreate) {
    return this.http.post(`${this.apiUrl}/review`, review, {
      withCredentials: true
    });
  }

  getReviewsForFragrance(fragranceId: number) {
    return this.http.get(`${this.apiUrl}/review?fragranceId=${fragranceId}`, {
      withCredentials: true
    });
  }

  deleteReviewById(reviewId: number) {
    return this.http.delete(`${this.apiUrl}/review?reviewId=${reviewId}`, {
      withCredentials: true
    });
  }

  /** NEW: fetch the N most recent reviews */
  getRecentFragranceReviews(n: number): Observable<FragranceReview[]> {
    return this.http.get<FragranceReview[]>(
      `${this.apiUrl}/review/recent?n=${n}`,
      { withCredentials: true }
    );
  }
}

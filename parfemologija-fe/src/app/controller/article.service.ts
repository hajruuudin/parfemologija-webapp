import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ArticleCreateModel, ArticleModel } from '../model/article-model';
import { environment } from '../../environments/environment';
import { Observable, map } from 'rxjs';

interface PagedResponse<T> {
  content: T[];
  totalPages: number;
}

@Injectable({ providedIn: 'root' })
export class ArticleService {
  private apiUrl = `${environment.apiUrl}/article`;

  constructor(private http: HttpClient) {}

  /** CREATE */
  addArticleToDatabase(request: ArticleCreateModel): Observable<ArticleModel> {
    return this.http.post<ArticleModel>(this.apiUrl, request, {
      withCredentials: true
    });
  }

  /** LIST (for browse & homepage) */
  getAllArticles(
    search: string,
    page: number,
    size: number
  ): Observable<PagedResponse<ArticleModel>> {
    return this.http.get<PagedResponse<ArticleModel>>(
      `${this.apiUrl}?search=${search}&page=${page}&size=${size}`, 
      { withCredentials: true }
    );
  }

  /** RECENT (for homepage) */
  getRecentArticles(): Observable<ArticleModel[]> {
    return this.getAllArticles('', 0, 5).pipe(map(r => r.content));
  }

  /** DETAIL (for overview) */
  getArticleById(id: string | number): Observable<ArticleModel> {
    return this.http.get<ArticleModel>(`${this.apiUrl}/${id}`, {
      withCredentials: true
    });
  }

  getUserArticles(){
    return this.http.get<ArticleModel[]>(`${this.apiUrl}/user`, {
      withCredentials: true
    })
  }
}

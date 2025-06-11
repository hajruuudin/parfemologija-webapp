import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MediaService {
  private apiUrl = environment.apiUrl;
  private IMGBB_API_URL = 'https://api.imgbb.com/1/upload';
  private IMGBB_API_KEY = '0402f2896b4fac3f53d2b3afba672554';

  constructor(private http: HttpClient) {}

  async uploadImage(file: File): Promise<string> {
    const base64 = await this.convertToBase64(file);
    const imageBase64 = base64.split(',')[1];

    const body = new HttpParams()
      .set('key', this.IMGBB_API_KEY)
      .set('image', imageBase64)
      .set('name', file.name)

    try {
      const response: any = await lastValueFrom(
        this.http.post(this.IMGBB_API_URL, body)
      );
      return response.data.url;
    } catch (error) {
      console.error('ImgBB upload failed:', error);
      throw new Error('Upload failed');
    }
  }

  private convertToBase64(file: File): Promise<string> {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.onload = () => resolve(reader.result as string);
      reader.onerror = reject;
      reader.readAsDataURL(file);
    });
  }

  storeImageUrlToDatabase(objectId: number, mediaCategory: string, imageUrl: string){
    return this.http.post(`${this.apiUrl}/media`, {mediaCategory, objectId, imageUrl}, {
      withCredentials: true
    });
  }
}


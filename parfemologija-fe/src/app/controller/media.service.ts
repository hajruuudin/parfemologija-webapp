import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MediaService {

  private IMGUR_UPLOAD_URL = 'https://api.imgur.com/3/image';
  private IMGUR_CLIENT_ID = 'YOUR_IMGUR_CLIENT_ID';

  constructor(private http: HttpClient) {}

  async uploadImage(file: File): Promise<string> {
    const base64 = await this.convertToBase64(file);

    const headers = new HttpHeaders({
      Authorization: `Client-ID ${this.IMGUR_CLIENT_ID}`,
      'Content-Type': 'application/json'
    });

    const body = {
      image: base64.split(',')[1],
      type: 'base64'
    };

    try {
      const response: any = await lastValueFrom(
        this.http.post(this.IMGUR_UPLOAD_URL, body, { headers })
      );

      return response.data.link;
    } catch (error) {
      console.error('Image upload failed:', error);
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
}

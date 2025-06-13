import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UserModel } from "../model/user-model";
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {
    private apiUrl: string = environment.apiUrl;

    constructor(private http: HttpClient){ }

    getUserById(id: number){
        return this.http.get<UserModel>(`${this.apiUrl}/user/${id}`, {
            withCredentials: true
        })
    }
}
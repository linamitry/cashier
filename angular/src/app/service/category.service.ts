import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Category} from "../model/category";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private readonly categoryGetUrl: string;

  constructor(private http: HttpClient) {
    this.categoryGetUrl = `http://localhost:8080/api/v1/category`;
  }
  public findAllCategory(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.categoryGetUrl}`);
  }
}

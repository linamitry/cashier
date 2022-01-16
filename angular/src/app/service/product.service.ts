import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../model/product";
import {User} from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private readonly productByCategoryUrl: string;
  private readonly getProductByIdsUrl: string;

  constructor(private http: HttpClient) {
    this.productByCategoryUrl = `http://localhost:8080/api/v1/product?category=`;
    this.getProductByIdsUrl = `http://localhost:8080/api/v1/product/get-products`;
  }

  public findProductByCategoryUrl(category: string): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.productByCategoryUrl}${category}`);
  }

  public getProductByIds(productIds: number[]): Observable<any> {
    return this.http.post(this.getProductByIdsUrl,
      productIds, {headers: new HttpHeaders({'Content-Type': 'application/json'}), observe: 'response'})
  }
}

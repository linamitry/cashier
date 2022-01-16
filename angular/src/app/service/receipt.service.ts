import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ReceiptRequest} from "../model/receipt-request";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ReceiptService {

  private readonly receiptCreateUrl: string;
  private readonly receiptUpdateUrl: string;
  private readonly getProductByReceiptUrl: string;

  constructor(private http: HttpClient) {
    this.receiptCreateUrl = 'http://localhost:8080/api/v1/receipt/';
    this.receiptUpdateUrl = 'http://localhost:8080/api/v1/receipt/cashier/';
    this.getProductByReceiptUrl = 'http://localhost:8080/api/v1/receipt/';
  }

  public create(receipt: ReceiptRequest): Observable<any> {
    return this.http.post(this.receiptCreateUrl,
      receipt, {headers: new HttpHeaders({'Content-Type': 'application/json'}), observe: 'response'})
  }

  public updateReceipt(receiptId: number, receipt: ReceiptRequest): Observable<any> {
    return this.http.put(this.receiptCreateUrl + receiptId,
      receipt, {headers: new HttpHeaders({'Content-Type': 'application/json'}), observe: 'response'})
  }

  public update(cashierId: number): Observable<any> {
    return this.http.get(this.receiptUpdateUrl + cashierId,
      {headers: new HttpHeaders({'Content-Type': 'application/json'}), observe: 'response'})
  }

  public getProductByReceipt(receiptId: number): Observable<any> {
    return this.http.get(this.getProductByReceiptUrl + receiptId,
      {headers: new HttpHeaders({'Content-Type': 'application/json'}), observe: 'response'})
  }
}

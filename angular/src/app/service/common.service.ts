import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor() { }

  private subjectName = new Subject<any>();

  sendUpdate(message: string) {
    this.subjectName.next({ text: message });
  }

  sendProducts(products: number[]) {
    this.subjectName.next({ text: products });
  }

  getUpdate(): Observable<any> {
    return this.subjectName.asObservable();
  }
}

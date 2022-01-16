import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from "../model/user";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly userRegisterUrl: string;
  private readonly userLoginUrl: string;
  private readonly userGetUrl: string;

  constructor(private http: HttpClient) {

    this.userRegisterUrl = 'http://localhost:8080/api/v1/cashier/register';
    this.userLoginUrl = 'http://localhost:8080/api/v1/cashier/login';
    this.userGetUrl = 'http://localhost:8080/api/v1/cashier/';
  }

  public register(user: User) {
    return this.http.post<User>(this.userRegisterUrl, user);
  }


  public login(user: User): Observable<any> {
    return this.http.post(this.userLoginUrl,
      user, {headers: new HttpHeaders({'Content-Type': 'application/json'}), observe: 'response'})
  }

  public getUser(id: number): Observable<any> {
    return this.http.get(this.userGetUrl + id,
      {headers: new HttpHeaders({'Content-Type': 'application/json'}), observe: 'response'});
  }
}

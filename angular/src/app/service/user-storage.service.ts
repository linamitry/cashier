import {Injectable} from '@angular/core';
import {User} from "../model/user";

const USER = 'user'

@Injectable({
  providedIn: 'root'
})

export class UserStorageService {

  user: User;

  constructor() {
    this.user = new User();
  }

  signOut(): void {
    window.sessionStorage.clear();
  }

  public saveUser(user: User) {
    window.sessionStorage.setItem(USER, JSON.stringify(user));
  }

  public getUser(): User {
    return JSON.parse(window.sessionStorage.getItem(USER)!!);
  }

  public isAuthenticated():boolean {
    return !!this.getUser()
  }
}

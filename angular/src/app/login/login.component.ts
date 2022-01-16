import {Component, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {User} from "../model/user";
import {UserService} from "../service/user.service";
import {UserStorageService} from "../service/user-storage.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Input() isAuthenticated: boolean=false
  errorMessage = '';

  user: User;

  constructor(private router: Router,
              private userService: UserService,
              private userStorage: UserStorageService) {
    this.user = new User();
  }

  onSubmit() {
    this.userService.login(this.user)
      .subscribe(
      data => {
        let loginedUser = data.body
        this.userStorage.saveUser(loginedUser);
        this.isAuthenticated = true;
        this.router.navigate([`/profile/${loginedUser.id}`])
      },
      err => {
        console.log(err)
        this.errorMessage = err.error.message;
      }
    );
  }

  ngOnInit(): void {
  }
}

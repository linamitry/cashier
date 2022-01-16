import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {UserStorageService} from "../service/user-storage.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public userId?: number;
  public isLogged: boolean = false;

  constructor(public userStorageService: UserStorageService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.router.events.subscribe(event => {
      if (event.constructor.name === "NavigationEnd") {
        this.isLogged = this.userStorageService.isAuthenticated()
        this.userId = this.userStorageService.getUser().id
      }
    })
  }

  logout(){
    this.userStorageService.signOut()
    this.router.navigate(['/login'])
  }
}

import {Component, OnInit} from '@angular/core';
import {ReceiptService} from "../service/receipt.service";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../service/user.service";
import {User} from "../model/user";
import {UserStorageService} from "../service/user-storage.service";
import {Product} from "../model/product";
import {Category} from "../model/category";


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  product?: Product[];
  category?: Category[];
  user: User = new User();
  userId?:number


  constructor(private route: ActivatedRoute,
              private userService: UserService,
              public userStorageService: UserStorageService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const id = Number.parseInt(params['userId']);
      this.userService.getUser(id).subscribe(data => {
        this.user = data.body
      })
    });
  }
}

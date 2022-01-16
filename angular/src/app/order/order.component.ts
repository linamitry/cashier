import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../service/user.service";
import {User} from "../model/user";
import {ReceiptService} from "../service/receipt.service";
import {UserStorageService} from "../service/user-storage.service";
import {ReceiptUpdate} from "../model/receipt-update";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  user: User = new User();
  receipt?: ReceiptUpdate[];

  constructor(private route: ActivatedRoute,
              private userService: UserService,
              private receiptService: ReceiptService,
              public userStorage: UserStorageService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = Number.parseInt(params['userId']);
      this.userService.getUser(id).subscribe(data => {
        this.user = data.body
      })
    })
    this.receiptService.update(this.userStorage.getUser().id!!).subscribe(data=>{
      this.receipt = data.body
    })
  }

}

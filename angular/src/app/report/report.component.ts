import { Component, OnInit } from '@angular/core';
import {User} from "../model/user";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  user: User = new User();

  constructor(private route: ActivatedRoute,
              private userService: UserService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = Number.parseInt(params['userId']);
      this.userService.getUser(id).subscribe(data => {
        this.user = data.body
      })
    })
  }

}

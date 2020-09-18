import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { Prenotation } from '../../models/prenotation';
import {NavigationExtras} from "@angular/router";
import { UserService } from '../../services/user.service';
import {Params, ActivatedRoute, Router} from '@angular/router';
import {Http} from '@angular/http';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

  constructor(public http:Http,
              public userService:UserService,
              public route:ActivatedRoute) { }

              public user:User = new User();
              public userId: number;
              public prenotation: Prenotation = new Prenotation();
             

  ngOnInit(): void {
       this.route.params.forEach((params: Params) => {
       this.userId = Number.parseInt(params['id']);
    });

    this.userService.getUser(this.userId).subscribe(
      res => {
        console.log(res.text());
        this.user=res.json();
        this.prenotation = this.user.prenotation;
      },
      error => {
        console.log(error);
      }
    );
  }

}

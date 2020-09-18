import { Component, OnInit } from '@angular/core';
import { Date } from '../../models/date';
import { User } from '../../models/user';
import {NavigationExtras} from "@angular/router";
import { Prenotation } from '../../models/prenotation';

import { PrenotationService } from '../../services/prenotation.service';
import {Params, ActivatedRoute, Router} from '@angular/router';
import {Http} from '@angular/http';




@Component({
  selector: 'app-prenotation-detail',
  templateUrl: './prenotation-detail.component.html',
  styleUrls: ['./prenotation-detail.component.css']
})
export class PrenotationDetailComponent implements OnInit {

  constructor(
		        public router:Router,
		        public http:Http,
		        public route:ActivatedRoute,
                public prenotationService: PrenotationService) { }

            public prenotationId: number;
            public prenotation:Prenotation = new Prenotation();
            public date:Date = new Date();
            public prenotationDone:boolean;
            public user:User = new User();
	    public noBloodType:boolean;
            


            onPrenotationDone(prenotation:Prenotation){
                 this.prenotationService.prenotationDone(prenotation).subscribe(
                    res=>{
                       console.log(res);
                       this.prenotationDone  = true;
                    },
                    error=>{
                    console.log(error.text());
			    if(error.text() === "No Blood Type!") this.noBloodType = true;
                    }
                 );
                

            }

            getUser(prenotationId: number){
               this.prenotationService.getUserByPrenotation(prenotationId).subscribe(
                    res => {
                      this.user = res.json();
                      console.log(this.user);
                    },
                    error => {
                      console.log(error.text());
                    }
               );
            }



  ngOnInit(): void {
  this.route.params.forEach((params: Params) => {
      this.prenotationId = Number.parseInt(params['id']);
    });

    this.prenotationService.getPrenotation(this.prenotationId).subscribe(
      res => {
        this.prenotation=res.json();
        console.log(this.prenotation);
        this.date = this.prenotation.date;
        this.prenotationDone = false;
        this.getUser(this.prenotation.id);
      },
      error => {
        console.log(error);
      }
    );

  }



}


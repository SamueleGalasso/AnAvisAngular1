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
            


            onPrenotationDone(prenotation:Prenotation){
                 this.prenotationService.prenotationDone(prenotation).subscribe(
                    res=>{
                       console.log(res);
                       this.prenotationDone  = true;
                    },
                    error=>{
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
        console.log(res.text());
        this.prenotation=res.json();
        this.date = this.prenotation.date;
        this.prenotationDone = false;
      },
      error => {
        console.log(error);
      }
    );
  }

}

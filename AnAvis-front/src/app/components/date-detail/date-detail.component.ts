import { Component, OnInit } from '@angular/core';
import { Date } from '../../models/date';
import { User } from '../../models/user';
import {NavigationExtras} from "@angular/router";
import { Prenotation } from '../../models/prenotation';
import { DateService } from '../../services/date.service';
import { PrenotationService } from '../../services/prenotation.service';
import {Params, ActivatedRoute, Router} from '@angular/router';
import {Http} from '@angular/http';
import {AppConst} from '../../constants/app-const';

@Component({
  selector: 'app-date-detail',
  templateUrl: './date-detail.component.html',
  styleUrls: ['./date-detail.component.css']
})
export class DateDetailComponent implements OnInit {

	public dateId: number;
	public date: Date = new Date();
  public user: User = new User();
  public prenotation: Prenotation = new Prenotation();
	public serverPath = AppConst.serverPath;


	public addDateSuccess: boolean = false;
	public notEnoughStock:boolean = false;

  constructor(
  	    public dateService:DateService,
		    public router:Router,
		    public http:Http,
		    public route:ActivatedRoute,
        public prenotationService: PrenotationService
  	) { }

  onPrenotate() {
      this.prenotationService.addPrenotation(
      this.date,
      this.date.remainingNumber
      ).subscribe(
      res=>{
      this.addDateSuccess = true;
        this.prenotation=res.json();
        console.log(this.prenotation,this.date);
        this.date.remainingNumber = this.date.remainingNumber-1;

        let navigationExtras: NavigationExtras = {
            queryParams: {
                "prenotation": JSON.stringify(this.prenotation)
            }
        };

        
      },
      error=>{
        console.log(error.text());
      }
      );
  }



  ngOnInit() {
  	this.route.params.forEach((params: Params) => {
  		this.dateId = Number.parseInt(params['id']);
  	});

  	this.dateService.getDate(this.dateId).subscribe(
  		res => {
  			this.date=res.json();
  		},
  		error => {
  			console.log(error);
  		}
  	);

  }

}
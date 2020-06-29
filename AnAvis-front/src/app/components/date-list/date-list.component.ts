import { Component, OnInit, ViewChild } from '@angular/core';
import { Date } from '../../models/date';
import { DateService } from '../../services/date.service';
import {Params, ActivatedRoute, Router} from '@angular/router';
import {Http} from '@angular/http';
import {AppConst} from '../../constants/app-const';




@Component({
  selector: 'app-date-list',
  templateUrl: './date-list.component.html',
  styleUrls: ['./date-list.component.css']
})
export class DateListComponent implements OnInit {

  displayedColumns: string[] = ['place', 'prenotationDate', 'remainingNumber', 'active'];

  public filterQuery = "";
  public rowsOnPage = 5;

  public selectedDate: Date;
  public dateList: Date[];
  public serverPath = AppConst.serverPath;

  constructor(
    public dateService:DateService,
    public router:Router,
    public http:Http,
    public route:ActivatedRoute
    ) { }

  onSelect(date: Date) {
    this.selectedDate = date;
    this.router.navigate(['/dateDetail', this.selectedDate.id]);
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      if(params['dateList']) {
        console.log("filtered date list");
        this.dateList = JSON.parse(params['dateList']);
      } else {
        this.dateService.getDateList().subscribe(
          res => {
            console.log(res.json());
            this.dateList = res.json();
          },
          err => {
            console.log(err);
          }
          );
      }
    });

  }
}
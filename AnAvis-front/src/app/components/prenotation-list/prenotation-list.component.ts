import { Component, OnInit, ViewChild } from '@angular/core';
import { Date } from '../../models/date';
import { Prenotation } from '../../models/prenotation';
import { DateService } from '../../services/date.service';
import { PrenotationService } from '../../services/prenotation.service';
import {Params, ActivatedRoute, Router} from '@angular/router';
import {Http} from '@angular/http';
import {AppConst} from '../../constants/app-const';


@Component({
  selector: 'app-prenotation-list',
  templateUrl: './prenotation-list.component.html',
  styleUrls: ['./prenotation-list.component.css']
})
export class PrenotationListComponent implements OnInit {

 displayedColumns: string[] = ['place', 'prenotationDate', 'active'];

  public filterQuery = "";
  public rowsOnPage = 5;

  public selectedPrenotation: Prenotation;
  public prenotationList: Prenotation[];
  public serverPath = AppConst.serverPath;

  constructor(
    public dateService:DateService,
    public prenotationService:PrenotationService,
    public router:Router,
    public http:Http,
    public route:ActivatedRoute) { }

    onSelect(prenotation: Prenotation) {
    this.selectedPrenotation = prenotation;
    this.router.navigate(['/prenotationDetail', this.selectedPrenotation.id]);
  }

  onRemoveOne(prenotation:Prenotation){
  this.prenotationService.sendDate(prenotation.id).subscribe(
res => {
location.reload();
console.log(res);

},
err => {
console.log(err);
}
);

  }



  

  ngOnInit(): void {
  this.route.queryParams.subscribe(params => {
      if(params['prenotationList']) {
        console.log("filtered prenotation list");
        this.prenotationList = JSON.parse(params['prenotationList']);
      } else {
        this.prenotationService.getPrenotationList().subscribe(
          res => {
            console.log(res.json());
            this.prenotationList = res.json();
          },
          err => {
            console.log(err);
          }
          );
      }
    });
  }

}



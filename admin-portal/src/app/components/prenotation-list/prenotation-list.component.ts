import { Component, OnInit, ViewChild } from '@angular/core';
import { Prenotation } from '../../models/prenotation';
import { User } from '../../models/user';
import { PrenotationService } from '../../services/prenotation.service';
import {Params, ActivatedRoute, Router} from '@angular/router';
import {Http} from '@angular/http';

@Component({
  selector: 'app-prenotation-list',
  templateUrl: './prenotation-list.component.html',
  styleUrls: ['./prenotation-list.component.css']
})
export class PrenotationListComponent implements OnInit {
displayedColumns: string[] = ['Firstname','Lastname', 'place', 'prenotationDate', 'active'];

  public filterQuery = "";
  public rowsOnPage = 5;

  public selectedPrenotation: Prenotation;
  public prenotationList: Prenotation[];
  public userList: User[];
  public noUserFound:boolean = false;


  constructor(
    public prenotationService:PrenotationService,
    public router:Router,
    public http:Http,
    public route:ActivatedRoute) { }

    

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

  onSelect(prenotation: Prenotation) {
    this.selectedPrenotation = prenotation;
    this.router.navigate(['/viewPrenotation', this.selectedPrenotation.id]);
  }

  onCheckOne(prenotation:Prenotation){
      this.prenotationService.checkPrenotation(prenotation).subscribe(
          res => {
          console.log(prenotation);
          console.log(res);
          location.reload();
          

          },
          error=>{
          console.log(error.text());
          let errorMessage = error.text();
          if(errorMessage==="No User Found!") this.noUserFound = true;
          }
      );
  }

  getUser(){
    this.prenotationService.getPrenotationUserList().subscribe(
       res=>{
           this.userList=res.json();
           console.log(res);
       },
       error=>{
       console.log(error.text());
       }
    );
  }

  ngOnInit(): void {
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
    
  

}

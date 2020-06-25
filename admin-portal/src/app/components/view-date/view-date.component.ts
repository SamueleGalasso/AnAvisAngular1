import { Component, OnInit } from '@angular/core';
import {Params, ActivatedRoute, Router} from '@angular/router';
import {GetDateService} from '../../services/get-date.service';
import {Date} from '../../models/date';

@Component({
  selector: 'app-view-date',
  templateUrl: './view-date.component.html',
  styleUrls: ['./view-date.component.css']
})
export class ViewDateComponent implements OnInit {

public date:Date = new Date();
public dateId: number;

  constructor(public getDateService:GetDateService,
  public route:ActivatedRoute,
  public router:Router) { }

  onSelect(date:Date){
  this.router.navigate(['/editDate', this.date.id])
  .then(s => location.reload());

  }

  ngOnInit(): void {
  this.route.params.forEach((params:Params) => {
  this.dateId = Number.parseInt(params['id']);
  });
  this.getDateService.getDate(this.dateId).subscribe(
  res =>{
  this.date = res.json();
  },
  error => {
  console.log(error);
  }
  );
  }

}

import { Component, OnInit } from '@angular/core';
import {Date} from '../../models/date';
import {Params, ActivatedRoute, Router} from '@angular/router';
import {GetDateService} from '../../services/get-date.service';
import {EditDateService} from '../../services/edit-date.service';

@Component({
  selector: 'app-edit-date',
  templateUrl: './edit-date.component.html',
  styleUrls: ['./edit-date.component.css']
})
export class EditDateComponent implements OnInit {

  color: 'accent';
  checked = false;
  disabled = false;

public dateId: number;
public date: Date = new Date();
public dateUpdated: boolean;

  constructor(
  public editDateService: EditDateService,
  public getDateService: GetDateService,
  public route: ActivatedRoute,
  public router: Router) { }

  onSubmit(){
  this.editDateService.sendDate(this.date).subscribe(
  data => {
  this.dateUpdated = true;
  },
  error => console.log(error)
  );
  }

  ngOnInit(): void {
  this.route.params.forEach((params: Params) => {
  this.dateId = Number.parseInt(params['id']);
  });
  this.getDateService.getDate(this.dateId).subscribe(
  res => {
  this.date = res.json();
  },
  error => console.log(error)
  )
  }

}

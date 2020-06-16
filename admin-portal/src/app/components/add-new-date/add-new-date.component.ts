import { Component, OnInit } from '@angular/core';
import { Date } from '../../models/date';
import {AddDateService} from '../../services/add-date.service';


@Component({
  selector: 'app-add-new-date',
  templateUrl: './add-new-date.component.html',
  styleUrls: ['./add-new-date.component.css']
})
export class AddNewDateComponent implements OnInit {

  color: 'primary';
  checked = false;
  disabled = false;

  public newDate: Date = new Date();
  public dateAdded: boolean;


  constructor(public addDateService:AddDateService) { }

  onSubmit() {
  	this.addDateService.sendDate(this.newDate).subscribe(
  		res => {
  			this.dateAdded=true;
  			this.newDate = new Date();
  			this.newDate.active=true;
  		},
  		error => {
  			console.log(error);
  		}
  	);
  }

  ngOnInit() {
  	this.dateAdded=false;
  	this.newDate.active=true;
  }

}

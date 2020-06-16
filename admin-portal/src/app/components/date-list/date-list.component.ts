import { Component, OnInit } from '@angular/core';
import {Date} from '../../models/date';
import {Router} from '@angular/router';
import {LoginService} from '../../services/login.service';
import {GetDateListService} from '../../services/get-date-list.service';
import {RemoveDateService} from '../../services/remove-date.service';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';

@Component({
selector: 'app-date-list',
templateUrl: './date-list.component.html',
styleUrls: ['./date-list.component.css']
})
export class DateListComponent implements OnInit {

public selectedDate: Date;
public checked: boolean;
public dateList: Date[];
public allChecked: boolean;
public removeDateList: Date[] = new Array();

constructor(public getDateListService: GetDateListService,
public removeDateService: RemoveDateService,
public router:Router,
public dialog:MatDialog) { }

openDialog(date:Date){
let dialogRef = this.dialog.open(DialogResultExampleDialog);
dialogRef.afterClosed().subscribe(
result => {
console.log(result);
if(result == "yes"){
this.removeDateService.sendDate(date.id).subscribe(
res => {
console.log(res);
this.getDateList();
},
err => {
console.log(err);
}
);
}
}
);

}

updateSelected(checked: boolean){
if(checked){
this.allChecked = true;
this.removeDateList = this.dateList.slice();
}else{
this.allChecked=false;
this.removeDateList=[];
}
}


updateRemoveDateList(checked: boolean,
date:Date){
if(checked){
this.removeDateList.push(date);
}else{
this.removeDateList.splice(this.removeDateList.indexOf(date), 1);
}
}



removeSelectedDates(){
let dialogRef = this.dialog.open(DialogResultExampleDialog);
dialogRef.afterClosed().subscribe(
result => {
console.log(result);
if(result == "yes"){
for(let date of this.removeDateList){
this.removeDateService.sendDate(date.id).subscribe(
res => {
},
err => {
}
);
}
location.reload();
}
}
);
}





onSelect(date:Date) {
this.selectedDate = date;
this.router.navigate(['/viewDate', this.selectedDate.id]);
}

getDateList(){
this.getDateListService.getDateList().subscribe(
res => {
console.log(res.json());
this.dateList = res.json();
},
error => {
console.log(error);
}

);

}

ngOnInit(): void {
this.getDateList();

}

}

@Component({
selector: 'dialog-result-example-dialog',
templateUrl: './dialog-result-example-dialog.html'

})
export class DialogResultExampleDialog{
constructor(public dialogRef: MatDialogRef<DialogResultExampleDialog>){}

}


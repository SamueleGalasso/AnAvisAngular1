import { Component, OnInit, ViewChild } from '@angular/core';
import { Prenotation } from '../../models/prenotation';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import {Params, ActivatedRoute, Router} from '@angular/router';
import {Http} from '@angular/http';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})

export class UserListComponent implements OnInit {
displayedColumns: string[] = ['firstName','lastName', 'username', 'gruppoSanguigno', 'enabled'];

  public filterQuery = "";
  public rowsOnPage = 5;

  public selectedUser: User;
  public userList: User[];
  public dataSource = new MatTableDataSource();


  constructor(
    public router:Router,
    public http:Http,
    public route:ActivatedRoute,
    public userService:UserService) { }

    


  onSelect(user: User) {
    this.selectedUser = user;
    this.router.navigate(['/viewUser', this.selectedUser.id]);
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  assignUserList(userList: User[]){
      this.dataSource = new MatTableDataSource(userList);
  }


  

  ngOnInit(): void {
   this.userService.getUserList().subscribe(
          res=>{

          this.userList = res.json();
          console.log(this.userList);
          this.assignUserList(this.userList);

          },
          error => {
          console.log(error.text());

          }

     
    );
        
      }
    
  

}


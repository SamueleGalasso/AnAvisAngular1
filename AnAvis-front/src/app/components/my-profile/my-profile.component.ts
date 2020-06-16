import { Component, OnInit } from '@angular/core';
import {AppConst} from '../../constants/app-const';
import {UserService} from '../../services/user.service';
import {LoginService} from '../../services/login.service';
import {User} from '../../models/user';
import {Router} from '@angular/router';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

public serverPath = AppConst.serverPath;
public loginError:boolean;
public loggedIn:boolean;
public credential = {'username':'', 'password':''};

public user: User = new User();
public updateSuccess: boolean;
public newPassword: string;
public incorrectPassword: boolean;
public currentPassword: string;
public usernameExists:boolean = false;
public emailExists:boolean = false;
public dataFetched:boolean = false;
displayedColumns: string[] = ['place', 'prenotationDate', 'active'];

public selectedProfileTab: number = 0;

  constructor(
   public loginService: LoginService,
   public userService: UserService,
   public router: Router
   ) { }

   selectedProfileChange(val: number) {
  this.selectedProfileTab = val;
  }

  onUpdateUserInfo () {
  	this.userService.updateUserInfo(this.user, this.newPassword, this.currentPassword).subscribe(
  		res => {
  			console.log(res.text());
  			this.updateSuccess=true;
  			this.loggedIn = false;
  			this.router.navigate(['/myAccount'])
  			.then(() => {
            window.location.reload();
          });
  			
  		},
  		error => {
  			console.log(error.text());
  			let errorMessage = error.text();
  			if(errorMessage==="Incorrect current password!") this.incorrectPassword=true;
  			if(errorMessage==="currentPasswordEmpty") this.incorrectPassword=true;
  			if(errorMessage==="usernameExists") this.usernameExists=true;
  			if(errorMessage==="emailExists") this.emailExists=true;
  		}
  	);
  }

   getCurrentUser() {
  	this.userService.getCurrentUser().subscribe(
  		res => {
  			this.user = res.json();
  			this.dataFetched  = true;
  		},
  		err => {
  			console.log(err);
  		}
  	);
  }

  

  ngOnInit(): void {

  this.loginService.checkSession().subscribe(
  		res => {
  			this.loggedIn = true;
  		},
  		error => {
  			this.loggedIn = false;
  			console.log("inactive session");
  			this.router.navigate(['/myAccount']);
  		}
  	);

  	this.getCurrentUser();
  }

}

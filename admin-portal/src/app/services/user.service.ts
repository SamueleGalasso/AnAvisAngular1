import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {User} from '../models/user';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(public http:Http) { }

  getUserList(){
    let url = "http://127.0.0.1:8181/user/userList";

    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem("xAuthToken")
    });
    return this.http.get(url, {headers: tokenHeader});
  }

  getUser(userId: number) {
  let url = "http://localhost:8181/user/"+userId;
    
    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    console.log("sendDate");

    return this.http.get(url, {headers: headers});
  }

  activateUser(user: User){
  let url = "http://localhost:8181/user/activateUser";
    
    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    console.log("checkPrenotation");

    return this.http.post(url, user, {headers: headers});
  }

  disableUser(user: User){
  let url = "http://localhost:8181/user/disableUser";
    
    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    console.log("checkPrenotation");

    return this.http.post(url, user, {headers: headers});
  }
}

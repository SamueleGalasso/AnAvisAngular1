import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Date} from '../models/date';


@Injectable({
  providedIn: 'root'
})
export class AddDateService {

  constructor(public http:Http) { }

  sendDate(date:Date){
  let url = "http://localhost:8181/date/add";
    
    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    console.log("sendDate");

    return this.http.post(url, JSON.stringify(date), {headers: headers});
  }
  }



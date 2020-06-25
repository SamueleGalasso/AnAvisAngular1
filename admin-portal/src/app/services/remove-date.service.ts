import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Date} from '../models/date';

@Injectable({
  providedIn: 'root'
})
export class RemoveDateService {

  constructor(public http:Http) { }

  sendDate(dateId: number){
  let url = "http://localhost:8181/date/remove";
    
    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    console.log("udpateDate");

    return this.http.post(url, dateId, {headers: headers});
  }
}

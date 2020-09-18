import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
@Injectable({
  providedIn: 'root'
})
export class GetDateListService {

  constructor(public http:Http) { }

  getDateList(){
  let url = "http://localhost:8181/date/dateList";
    
    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    console.log("sendDate");

    return this.http.get(url, {headers: headers});
  }
}

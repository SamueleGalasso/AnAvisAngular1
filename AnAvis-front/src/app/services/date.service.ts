import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {AppConst} from '../constants/app-const';

@Injectable()
export class DateService {

  constructor(private http:Http) { }

  getDateList() {
    let url = AppConst.serverPath+"/date/dateList";

    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem("xAuthToken")
    });
    return this.http.get(url, {headers: tokenHeader});
  }

  getDate(id:number) {
    let url = AppConst.serverPath+"/date/"+id;

    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem("xAuthToken")
    });
    return this.http.get(url, {headers: tokenHeader});
  }

  searchDate(keyword:string) {
    let url = AppConst.serverPath+"/date/searchDate";

    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem("xAuthToken")
    });
    return this.http.post(url, keyword, {headers: tokenHeader});
  }

}

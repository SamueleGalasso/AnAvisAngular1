import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {AppConst} from '../constants/app-const';
import {Date} from '../models/date';
import {User} from '../models/user';


@Injectable({
  providedIn: 'root'
})
export class PrenotationService {

  constructor(private http:Http) { }

  addPrenotation(
  date:Date,
  qty:number
  ){

  let url = AppConst.serverPath+"/prenotation/add";

  let prenotation = {
  "date" : date,
  "qty" : qty
  }

    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem("xAuthToken")
    });
    return this.http.post(url, prenotation, {headers: tokenHeader});

  }

  getPrenotation(id:number) {
    let url = AppConst.serverPath+"/prenotation/"+id;

    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem("xAuthToken")
    });
    return this.http.get(url, {headers: tokenHeader});
  }

  getUserPrenotation(){

    let url = AppConst.serverPath+"/prenotation/getUserPrenotation";

    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem("xAuthToken")
    });
    return this.http.get(url, {headers: tokenHeader});
  }

  sendDate(prenotationId: number){
  let url = "http://localhost:8181/prenotation/remove/";
    
    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    console.log("udpateDate");

    return this.http.post(url, prenotationId, {headers: headers});
  }
  

  getPrenotationList(){
    let url = AppConst.serverPath+"/prenotation/getPrenotationList";

    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem("xAuthToken")
    });
    return this.http.get(url, {headers: tokenHeader});
  }
}

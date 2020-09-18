import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Date} from '../models/date';
import {User} from '../models/user';
import{Prenotation} from '../models/prenotation';

@Injectable({
  providedIn: 'root'
})
export class PrenotationService {

  constructor(private http:Http) { }

  getPrenotation(prenotationId: number) {
  let url = "http://localhost:8181/prenotation/"+prenotationId;
    
    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    console.log("sendDate");

    return this.http.get(url, {headers: headers});
  }

getPrenotationList(){
    let url = "http://127.0.0.1:8181/prenotation/prenotationList";

    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem("xAuthToken")
    });
    return this.http.get(url, {headers: tokenHeader});
  }

  getPrenotationUserList(){
    let url = "http://127.0.0.1:8181/prenotation/prenotationUserList";

    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem("xAuthToken")
    });
    return this.http.get(url, {headers: tokenHeader});
  }

  sendDate(prenotationId: number){
  let url = "http://localhost:8181/prenotation/admin/remove";
    
    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    console.log("udpateDate");

    return this.http.post(url, prenotationId, {headers: headers});
  }

  checkPrenotation(prenotation: Prenotation){
  let url = "http://localhost:8181/prenotation/checkPrenotation";
    
    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    console.log("checkPrenotation");

    return this.http.post(url, prenotation, {headers: headers});
  }

  prenotationDone(prenotation: Prenotation){
  let url = "http://localhost:8181/prenotation/prenotationDone";
    
    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    console.log("checkPrenotation");

    return this.http.post(url, prenotation, {headers: headers});
  }

  getUserByPrenotation(prenotationId: number){
  let url = "http://127.0.0.1:8181/prenotation/getUserByPrenotation/"+prenotationId;

    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem("xAuthToken")
    });
    return this.http.get(url, {headers: tokenHeader});

  }


}


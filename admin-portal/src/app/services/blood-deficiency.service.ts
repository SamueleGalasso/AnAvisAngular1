import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';

@Injectable({
  providedIn: 'root'
})
export class BloodDeficiencyService {

  constructor(public http: Http) { }

  getBloodCount(){
  let url = "http://localhost:8181/prenotation/bloodCount";
    
    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    console.log("sendDate");

    return this.http.get(url, {headers: headers});
  }

  

   getBloodDeficiency(bloodType:string){
    let url = "http://localhost:8181/blood/deficiency";
    
    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    console.log("bloodType");

    return this.http.post(url, JSON.stringify(bloodType), {headers: headers});
  }

}

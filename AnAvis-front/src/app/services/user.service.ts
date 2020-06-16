import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {AppConst} from '../constants/app-const';
import {User} from '../models/user';


@Injectable({
  providedIn: 'root'
})
export class UserService {
private serverPath: string = AppConst.serverPath;

  constructor(public http:Http) { }

  newUser(username: string, email:string) {
  let url = this.serverPath+'/user/newUser';
  let userInfo = {
  "username" : username,
  "email" : email
  }

  let tokenHeader = new Headers({
  'Content-Type' : 'application/json',
  'x-auth-token' : localStorage.getItem('xAuthToken')
  });

  return this.http.post(url, JSON.stringify(userInfo), {headers : tokenHeader});
  }

  updateUserInfo(user: User, newPassword: string, currentPassword: string) {
  let url = this.serverPath + "/user/updateUserInfo";
  let userInfo = {
     "id" : user.id,
     "firstName" : user.firstName,
     "lastName" : user.lastName,
     "username" : user.username,
     "currentPassword" : currentPassword,
     "email" : user.email,
     "newPassword" : newPassword,
     "codiceFiscale" : user.codiceFiscale,
     "paese" : user.paese,
     "citta" : user.citta,
     "phone" : user.phone,
     "birthDate" : user.birthDate,
     "extraUserInfo" : user.extraUserInfo,
     "gruppoSanguigno" : user.gruppoSanguigno
  };
   
   let tokenHeader = new Headers({
   'Content-Type' : 'application/json',
   'x-auth-token' : localStorage.getItem("xAuthToken")
   });

   return this.http.post(url, JSON.stringify(userInfo), {headers:tokenHeader});


  }

  retrievePassword(email:string) {
  let url = this.serverPath+'/user/forgetPassword';
  let userInfo = {
  "email" : email
  }

  let tokenHeader = new Headers({
  'Content-Type' : 'application/json',
  'x-auth-token' : localStorage.getItem('xAuthToken')
  });

  return this.http.post(url, JSON.stringify(userInfo), {headers : tokenHeader});
  }

   
   getCurrentUser() {

   let url = this.serverPath+'/user/getCurrentUser';

  let tokenHeader = new Headers({
  'Content-Type' : 'application/json',
  'x-auth-token' : localStorage.getItem('xAuthToken')
  });

  return this.http.get(url, {headers : tokenHeader});


   }


}

import {Prenotation} from './prenotation'
export class User {

   public id: number;
   public firstName: string;
   public lastName: string;
   public username: string;
   public password: string;
   public birthDate: string;
   public codiceFiscale: string;
   public gruppoSanguigno: string;
   public paese: string;
   public citta: string;
   public email: string;
   public phone: string;
   public extraUserInfo: string;
   public enabled: boolean;
   public prenotation: Prenotation;
   
}
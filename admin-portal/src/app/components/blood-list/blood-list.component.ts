import { Component, OnInit } from '@angular/core';
import {Bloodcount} from '../../models/bloodcount';
import {User} from '../../models/user';
import {BloodDeficiencyService} from '../../services/blood-deficiency.service';

@Component({
  selector: 'app-blood-list',
  templateUrl: './blood-list.component.html',
  styleUrls: ['./blood-list.component.css']
})
export class BloodListComponent implements OnInit {

  constructor(public bloodService: BloodDeficiencyService) { }

  public bloodcount: Bloodcount = new Bloodcount();
  public user: User = new User();
  public bloodRequest:boolean;



  displayedColumns: string[] = ['A', 'B', 'AB', '0'];


  onBloodDeficiency(bloodType: string){
      this.bloodService.getBloodDeficiency(bloodType).subscribe(
          res=>{
             this.bloodRequest = true;
             console.log(res);
          },
          error=>{
            console.log(error.text());
          }
      );

  }

  ngOnInit(): void {

  this.bloodService.getBloodCount().subscribe(
      res=>{
         this.bloodRequest = false;
         this.bloodcount = res.json();
         console.log(this.bloodcount);

      },
      error=>{
      console.log(error.text());

      }

  );



  }

}

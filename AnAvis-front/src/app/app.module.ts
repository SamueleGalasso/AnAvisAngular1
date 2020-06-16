import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import 'hammerjs';
import {NavBarComponent} from './components/nav-bar/nav-bar.component';
import {MatButtonModule} from '@angular/material/button';
import {MatTabsModule} from '@angular/material/tabs';

import {LoginService} from './services/login.service';
import {UserService} from './services/user.service';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core'

import {DateService} from './services/date.service';
import {PrenotationService} from './services/prenotation.service';





import {routing} from './app.routing';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { MyAccountComponent } from './components/my-account/my-account.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';


import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { DateListComponent } from './components/date-list/date-list.component';
import {DataFilterPipe} from './components/date-list/data-filter.pipe';
import {MatTableModule} from '@angular/material/table';
import { DateDetailComponent } from './components/date-detail/date-detail.component';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import { PrenotationListComponent } from './components/prenotation-list/prenotation-list.component';
import { PrenotationDetailComponent } from './components/prenotation-detail/prenotation-detail.component';





@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavBarComponent,
    MyAccountComponent,
    DateListComponent,
    DataFilterPipe,
    DateDetailComponent,
    MyProfileComponent,
    PrenotationListComponent,
    PrenotationDetailComponent,
    
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing,
    MatButtonModule,
    MatTabsModule,
    BrowserAnimationsModule,
    MatProgressSpinnerModule,
    MatTableModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatInputModule,
    MatSelectModule  
  ],
  providers: [
  LoginService,
  UserService,
  DateService,
  PrenotationService,
  MatNativeDateModule,
  MatDatepickerModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

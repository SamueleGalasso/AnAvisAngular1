import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { FormsModule} from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { HttpModule} from '@angular/http';
import {routing} from './app.routing';
import { AppComponent } from './app.component';
import 'hammerjs';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { LoginComponent } from './components/login/login.component';
import {MatListModule} from '@angular/material/list';
import {MatDialogModule} from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table'

import {LoginService} from './services/login.service';
import {AddDateService} from './services/add-date.service';
import {GetDateListService} from './services/get-date-list.service';
import {GetDateService} from './services/get-date.service';
import {EditDateService} from './services/edit-date.service';
import {RemoveDateService} from './services/remove-date.service';
import {BloodDeficiencyService} from './services/blood-deficiency.service';
import {PrenotationService} from './services/prenotation.service';
import {UserService} from './services/user.service';



import {MatGridListModule} from '@angular/material/grid-list';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

import { AddNewDateComponent } from './components/add-new-date/add-new-date.component';
import {MatSelectModule} from '@angular/material/select';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { DateListComponent, DialogResultExampleDialog } from './components/date-list/date-list.component';
import { ViewDateComponent } from './components/view-date/view-date.component';
import { EditDateComponent } from './components/edit-date/edit-date.component';
import { BloodListComponent } from './components/blood-list/blood-list.component';
import { PrenotationListComponent } from './components/prenotation-list/prenotation-list.component';
import { PrenotationDetailComponent } from './components/prenotation-detail/prenotation-detail.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { UserDetailComponent } from './components/user-detail/user-detail.component';




@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    LoginComponent,
    AddNewDateComponent,
    DateListComponent,
    ViewDateComponent,
    EditDateComponent,
    DialogResultExampleDialog,
    BloodListComponent,
    PrenotationListComponent,
    PrenotationDetailComponent,
    UserListComponent,
    UserDetailComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    routing,
    MatButtonModule,
    MatToolbarModule,
    MatGridListModule,
    MatInputModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatListModule,
    MatDialogModule,
    MatTableModule

  ],
  providers: [

  LoginService,
  AddDateService,
  GetDateListService,
  GetDateService,
  EditDateService,
  BloodDeficiencyService,
  PrenotationService

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

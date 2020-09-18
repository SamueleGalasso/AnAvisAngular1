import {ModuleWithProviders} from '@angular/core';
import {Routes,RouterModule} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {AddNewDateComponent} from './components/add-new-date/add-new-date.component';
import {DateListComponent} from './components/date-list/date-list.component';
import {ViewDateComponent} from './components/view-date/view-date.component';
import {EditDateComponent} from './components/edit-date/edit-date.component';
import {BloodListComponent} from './components/blood-list/blood-list.component';
import {PrenotationListComponent} from './components/prenotation-list/prenotation-list.component';
import {UserListComponent} from './components/user-list/user-list.component';
import {PrenotationDetailComponent} from './components/prenotation-detail/prenotation-detail.component';
import {UserDetailComponent} from './components/user-detail/user-detail.component';


const appRoutes: Routes = [

   {
     path: '',
     redirectTo: '/login',
     pathMatch: 'full'
   },
   {
     path: 'login',
     component: LoginComponent
   },
   {
     path: 'addNewDate',
     component: AddNewDateComponent
   },
   {
     path: 'dateList',
     component: DateListComponent
   },
   {
     path: 'viewDate/:id',
     component: ViewDateComponent
   },
   {
     path: 'editDate/:id',
     component: EditDateComponent
   },
   {
     path: 'bloodList',
     component: BloodListComponent
   },
   {
     path: 'prenotationList',
     component: PrenotationListComponent
   },
   {
     path: 'viewPrenotation/:id',
     component: PrenotationDetailComponent
   },
   {
     path: 'userList',
     component: UserListComponent
   },
   {
     path: 'viewUser/:id',
     component: UserDetailComponent
   }

];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);

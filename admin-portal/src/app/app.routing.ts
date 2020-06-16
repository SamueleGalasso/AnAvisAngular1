import {ModuleWithProviders} from '@angular/core';
import {Routes,RouterModule} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {AddNewDateComponent} from './components/add-new-date/add-new-date.component';
import {DateListComponent} from './components/date-list/date-list.component';
import {ViewDateComponent} from './components/view-date/view-date.component';
import {EditDateComponent} from './components/edit-date/edit-date.component';

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
   }

];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);

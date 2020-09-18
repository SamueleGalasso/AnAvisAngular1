import {ModuleWithProviders} from '@angular/core';
import {Routes,RouterModule} from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {MyAccountComponent} from './components/my-account/my-account.component';
import {DateListComponent} from './components/date-list/date-list.component';
import {DateDetailComponent} from './components/date-detail/date-detail.component';
import {MyProfileComponent} from './components/my-profile/my-profile.component';
import {PrenotationListComponent} from './components/prenotation-list/prenotation-list.component';
import {PrenotationDetailComponent} from './components/prenotation-detail/prenotation-detail.component';

const appRoutes: Routes = [

   {
     path: '',
     redirectTo: '/home',
     pathMatch: 'full'
   },
   {
     path: 'home',
     component: HomeComponent
   },
   {
     path: 'myAccount',
     component: MyAccountComponent
   },
   {
     path: 'dateList',
     component: DateListComponent
   },
   {
     path: 'dateDetail/:id',
     component: DateDetailComponent
   },
   {
     path: 'myProfile',
     component: MyProfileComponent
   },
   {
     path: 'prenotationList',
     component: PrenotationListComponent
   },
   {
     path: 'prenotationDetail/:id',
     component: PrenotationDetailComponent
   }




];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
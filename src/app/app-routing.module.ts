import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CheckoutComponent } from './Main/checkout/checkout.component';
import { HomeComponent } from './Main/home/home.component';

const routes: Routes = [
  { path: 'checkout', component: CheckoutComponent },
  { path: 'home', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

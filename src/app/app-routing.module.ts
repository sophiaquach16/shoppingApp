import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartComponent } from './Main/cart/cart.component';
import { CheckoutComponent } from './Main/checkout/checkout.component';
import { HomeComponent } from './Main/home/home.component';
import { ProfileUserComponent } from './Main/profile-user/profile-user.component';

const routes: Routes = [
  { path: 'checkout', component: CheckoutComponent },
  { path: 'home', component: HomeComponent },
  { path: 'cart', component: CartComponent },
  { path: 'checkout', component: CheckoutComponent },
  { path: 'profile', component: ProfileUserComponent },
  
  { path: '' , redirectTo:'/home', pathMatch:'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

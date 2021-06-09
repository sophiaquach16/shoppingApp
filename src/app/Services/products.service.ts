import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../Main/home/user';
import { Product } from '../Main/home/productsMock';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http: HttpClient) {}

  getAllProducts(){
    let url = 'https://fakestoreapi.com/products';
    return this.http.get<any>(url);
  }

  getCartForUser(userId: number){
    let url = 'https://fakestoreapi.com/carts/user/${userId}';
    return this.http.get<User>(url);
  }

  addProductToCart(prduct: Product, user: User){
    this.getCartForUser(user.id);
  }

}

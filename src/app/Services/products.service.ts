import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../Main/home/user';
import { Product } from '../Main/home/productsMock';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http: HttpClient) {}

  // getAllProducts(){
  //   let url = 'https://fakestoreapi.com/products';
  //   return this.http.get<any>(url);
  // }

  getAllProducts(){
    let url = 'http://localhost:8080/api/allProducts';
    return this.http.get<any>(url);
  }

  addProductToCart(product: Product, userId: number){
    let url = 'http://localhost:8080/api/'+userId+'/addProductToCart';
    // this.getCartForUser(user.id);
    return this.http.put<any>(url, product.product_id);
  }

  getCartForUser(userId: number){
    let url = 'http://localhost:8080/api/'+userId+'/cart';
    return this.http.get<any>(url);
  }

  getProductById(productId: string){
    let url = 'http://localhost:8080/api/'+productId+'product';
    return this.http.get<any>(url);
  }

}

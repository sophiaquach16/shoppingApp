import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http: HttpClient) {}

  getAllProducts(){
    let url = 'https://fakestoreapi.com/products';
    return this.http.get<any>(url);
  }

}

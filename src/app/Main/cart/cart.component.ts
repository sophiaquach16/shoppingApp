import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Product, products } from '../home/productsMock';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { ProductsService } from 'src/app/Services/products.service';
import { forkJoin, zip, combineLatest, Subject } from 'rxjs';
import { withLatestFrom, take, first } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  code = "";
  products: Product[] = [];
  // cart: Cart[] = [];
  cartMap = new Map<Product, number>();
  cartPdt: Product[] = [];
  closeResult='';
  id: any;
  title: any;
  price: any;
  description: any;
  category: any;
  image: any;
  quantity : any;
  selectedLevel: any;
 

  constructor(private cartService: ProductsService, private productService: ProductsService, private modalService: NgbModal, private http: HttpClient) { }

  ngOnInit(): void {
   this.cartService.getCartForUser(1).subscribe(
      response => {
        this.cartMap = response;
        console.log(this.cartMap);
      }
    )
  }
  
  totalQuantity(){
    return 10; //Need to fix this method
  }

  getTotalPriceBeforeFees(){
    let total = 0;
    this.products.forEach(function(products){
      total = total + products.retail_price;
    })
    return (Math.round(total * 100) / 100).toFixed(2);;
      
  }

  getTax(){
    return (Math.round((parseFloat(this.getTotalPriceBeforeFees()) * 0.15) * 100) / 100).toFixed(2);
  }

  
  getTotalPrice(){
    var shippingCost = (document.querySelector("#shipping") as unknown as HTMLSelectElement).value;
    var total = parseFloat(this.getTotalPriceBeforeFees()) + parseFloat(this.getTax()) + parseFloat(shippingCost);
    return (Math.round(total * 100) / 100).toFixed(2);
  }

  getTotalPriceWithDiscount(){
    var shippingCost = (document.querySelector("#shipping") as unknown as HTMLSelectElement).value;
    var total = parseFloat(this.getTotalPriceBeforeFees()) + parseFloat(this.getTax()) + parseFloat(shippingCost);
    total = total * 0.90;
    return (Math.round(total * 100) / 100).toFixed(2);
  }
    
  

}

import { Component, OnInit } from '@angular/core';


import { Product } from '../home/productsMock';


import { NgForm } from '@angular/forms';

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
  keys: Product[] =[];
  cartMap=new Map();
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
  total: number = 0;
 

  constructor(private cartService: ProductsService, private productService: ProductsService, private modalService: NgbModal, private http: HttpClient) { }

  // ngOnInit(): void {
  //  this.cartService.getCartForUser(1).subscribe(
  //     response => {
  //       this.cartMap = response;
  //       // this.keys = Array.from( this.cartMap.keys()) ;
  //       console.log(this.cartMap);
  //       // const pdts = Object.keys(this.cartMap);
  //       // console.log(pdts);
  //       // for (const key of pdts.keys()) {
  //       //   console.log(key);        
  //       // }
        
  //     }
  //   )
  // }

  ngOnInit(): void{
    this.productService.getCartForUser(1).subscribe(response => {
      this.cartMap = response;
      // console.log(cartMap);
      this.keys = Object.keys(this.cartMap) as unknown as Product[];
      let currPdt = this.keys[0];
      // console.log(this.cartMap.entries().next().value);

      console.log(this.keys[0]['product_id']);
      //  Object.keys(cartMap).forEach(key => {
      //   var pdts = cartMap[key];
      //   console.log(pdts);
      // });
      // console.log('array is ' + this._postsArray.toString());
    
       });
  }
  
  totalQuantity(){
    return 10; //Need to fix this method
  }

  getTotalPriceBeforeFees(){

    // this.products.forEach(function(products){
    //   total = total + products.retail_price;
    // })
    // return (Math.round(total * 100) / 100).toFixed(2);
    return (Math.round(this.total * 100) / 100).toFixed(2);
      
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
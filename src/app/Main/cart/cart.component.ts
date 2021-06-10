import { Component, OnInit } from '@angular/core';


import { Item, Product } from '../home/productsMock';


import { NgForm } from '@angular/forms';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { ProductsService } from 'src/app/Services/products.service';
import { forkJoin, zip, combineLatest, Subject } from 'rxjs';
import { withLatestFrom, take, first } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { stringify } from '@angular/compiler/src/util';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  code = "";
  products: Product[] = [];
  keys: string[] =[];
  resultMap: Map<string, Number>=new Map();
  cartMap: Map<Product, Number>=new Map();
  cartPdt: Product[] = [];
  closeResult='';
  id: any;
  title: any;
  price: any;
  description: any;
  category: any;
  image: any;
  // quantity : any;
  selectedLevel: any;
  total: number = 0;
  currProduct!: Product;
  countPdt: number[] = [];
  i:number = 0;
  items: Item[] = [];
 

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
      this.resultMap = response;
      // console.log(this.resultMap);
      // console.log(this.resultMap.get('1'));
      // this.keys = Object.keys(this.resultMap); //array of products id as string
      // console.log(this.keys[0]); //test the first product id
      // this.keys = Array.from(Object.keys(this.resultMap));
      // this.countPdt = Object.values(this.resultMap); //array od pdt counts
      // console.log(response[this.keys[0]]);
      // console.log(this.countPdt);

      // for(let i=0; i<this.resultMap.size -1; i++){
      //   this.productService.getProductById(this.keys[i]).subscribe(response => {
      //             this.currProduct = response;
      //             // this.cartPdt.push(this.currProduct);
      //             // console.log(this.cartPdt);
      //             let currItem: Item
      //           });
      // }

      // for(let id of keys){
      //   this.productService.getProductById(id).subscribe(response => {
      //         this.currProduct = response;
      //         this.cartPdt.push(this.currProduct);
      //         console.log(this.cartPdt);


            // });
          
      // }

      Object.keys(this.resultMap).forEach((key: string) => {
        // console.log(this.resultMap.get(key));
        console.log("key "+key);
        console.log("value" + response[key]);

        // this.countPdt = Object.values(this.resultMap);
        // console.log(typeof(this.countPdt));
        // console.log("value: "+this.countPdt[0]);
        let quantity = response[key];
        console.log(quantity);
        this.productService.getProductById(key).subscribe(response1 => {
          this.currProduct = response1;
         
          let item: Item = {
            product: this.currProduct,
            quantity: quantity
          };
          console.log(quantity);
          this.items.push(item);
          // this.cartPdt.push(this.currProduct);
          console.log(this.items);
        });
      
      });
      console.log(this.items);
  
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
import { Component, OnInit } from '@angular/core';
import { ProductsService } from 'src/app/Services/products.service';
import { products } from '../home/productsMock';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  products = products;

  constructor(private currService: ProductsService) {}

  ngOnInit(): void {
    this.currService.getCartForUser(1).subscribe(response => {
      console.log(response);
    })
  }

  quantity: number = 1;
  counter : number = 1;

  minus(){
    if(this.counter != 1){
      this.counter--;
      this.quantity = this.counter;
    }
  }

  plus(){
    if(this.counter != 99){
      this.counter++;
      this.quantity = this.counter;
    }
  }
  
  totalQuantity(){
    return 10;
  }

  getTotalPriceBeforeFees(){
    let total = 0;
    products.forEach(function(product){
      total = total + product.price;
    })
    return (Math.round(total * 100) / 100).toFixed(2);;
      
  }

  getTax(){
    ;
    return (Math.round((parseFloat(this.getTotalPriceBeforeFees()) * 0.15) * 100) / 100).toFixed(2);
  }

  getTotalPrice(){
    var e = (document.getElementById("shipping") as HTMLSelectElement).value;
    var total = parseFloat(this.getTotalPriceBeforeFees()) * 1.15 + parseFloat(e);
    return (Math.round(total * 100) / 100).toFixed(2);
  }

  getCartForUser(){

  }

}

import { Component, OnInit } from '@angular/core';
import { ProductsService } from 'src/app/Services/products.service';
import { Product } from './productsMock';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  products: Product[] = [];

  constructor(private currService: ProductsService) {
    
   }

  ngOnInit(): void {
    this.currService.getAllProducts().subscribe(
      response => {
        this.products = response;
        console.log(response);
      }
    )
  }

}

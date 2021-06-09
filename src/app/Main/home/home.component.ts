import { Component, OnInit } from '@angular/core';
import { ProductsService } from 'src/app/Services/products.service';
import { Product } from './productsMock';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from './user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  products: Product[] = [];
  user!: User;


  closeResult='';
  id: any;
  title: any;
  price: any;
  description: any;
  category: any;
  image: any;

  constructor(private currService: ProductsService, private modalService: NgbModal) {
    
   }

  ngOnInit(): void {
    this.currService.getAllProducts().subscribe(
      response => {
        this.products = response;
        console.log(response);
      }
    )
  }

  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }
  
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  openDetails(targetModal: any, product: Product) {
      this.modalService.open(targetModal, {
      centered: true,
      backdrop: 'static',
      size: 'lg'
      });
   this.title = product.product_name;
   this.description = product.description;
   this.price = product.retail_price;
   this.category = product.product_category_tree;
 }

 addToCart(targetModal: any, product: Product){
   this.modalService.open(targetModal, {
    centered: true,
    backdrop: 'static',
    size: 'sm'
   });

   this.currService.addProductToCart(product, 1).subscribe(
     response => {
       console.log(response);
     }
   )
 }

}

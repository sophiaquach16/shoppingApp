import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  firstname = "";
  lastname = "";
  credit = "";
  ccname = "";
  ccnumber = "";
  username = "";
  address = "";
  address2 = "";
  country = "";
  province = "";
  zip = "";

  constructor(private formBuilder: FormBuilder) {}

  

  ngOnInit(): void {}

  onSubmit() { } 


}

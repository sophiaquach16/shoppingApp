import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile-user',
  templateUrl: './profile-user.component.html',
  styleUrls: ['./profile-user.component.css']
})
export class ProfileUserComponent implements OnInit {

  firstname = "Groot";
  lastname = "Groote";
  address = "123 rue Pineapple Montreal, QC H1A2H1";

  constructor() { }

  ngOnInit(): void {
  }

}

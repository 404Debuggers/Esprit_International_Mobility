import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  role:string | undefined;
  constructor() { }

  ngOnInit(): void {
    this.role = ""+sessionStorage.getItem("Role");
  }



}

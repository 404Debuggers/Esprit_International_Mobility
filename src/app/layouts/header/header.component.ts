import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent  {
  role:string | undefined;
  username:string | undefined
  constructor(private router: Router) { }

  ngOnInit(): void {
     this.role = ""+sessionStorage.getItem("Role");
     this.username = ""+sessionStorage.getItem("Username");
    // console.log(this.username)
     if(this.username == "no"){
       this.router.navigate(['login']);
     }
  }

  toogle(){
    const element = document.body as HTMLBodyElement
    element.classList.toggle('toggle-sidebar')
  }
   singout(){
     sessionStorage.removeItem("Token");
     sessionStorage.removeItem("Role");
     sessionStorage.setItem("Username","no");
  //   //sessionStorage.clear();
     this.router.navigate(['login']);
    }



}

import { Component, OnInit } from '@angular/core';
import {SignUpInfo} from "../auth/signup-info";
import {Router} from "@angular/router";
import {AuthService} from "../auth/auth.service";
import {first} from "rxjs";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {


  form: any = {};
  signupInfo: SignUpInfo | undefined;
  isSignUpFailed = false;
  errorMessage = '';
  constructor( private  authService : AuthService , private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(){
  this.signupInfo = new SignUpInfo(this.form.firstName, this.form.lastName,this.form.phone , this.form.cin, this.form.email,this.form.role ,this.form.password);
  this.authService.signUp(this.signupInfo).pipe(first()).subscribe(
    data => {
      this.isSignUpFailed = false ;
      this.router.navigate(['login']);
    },
    error => {
      this.errorMessage = error.error.message;
      this.isSignUpFailed = true;
    }
  )
  }

}

import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../shared/services/login-service.service';
import { Account } from '../../models/account'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LoginService]
})
export class LoginComponent implements OnInit {

  public email: string;
  public password: string;
  public account: Account;

  constructor(private api: LoginService, private router: Router) { }

  ngOnInit(): void {

  }

  inputChange(){
    console.log(this.email);
  }

  _login(){
    this.api._login(this.email, this.password).subscribe((data)=>{
      console.log(data);
      if(data['code'] == 200) this.router.navigateByUrl('/trang-chu');
    });
  }

  _register(){
    this.api._register(this.account).subscribe((data)=>{
      console.log(data);
      //if(data['code'] == 200) this.router.navigateByUrl('/trang-chu');
    });
  }

}

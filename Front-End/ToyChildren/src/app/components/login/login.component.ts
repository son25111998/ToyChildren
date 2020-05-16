import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountService } from 'src/app/shared/services/account.service';
import { CodeConstants } from 'src/app/shared/utils/code.constants';
import { Constant } from 'src/app/shared/utils/constant';
import { CustomerService } from 'src/app/shared/services/customer.service';
import { UrlConstants } from 'src/app/shared/utils/url.constants';
import { AccountInput } from '../../models/account-input';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import { LoginInput } from 'src/app/models/login-input';

/* Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [AccountService]
})
export class LoginComponent implements OnInit {

  emailFormControl = new FormControl('', [
    Validators.required,
  ]);

  passwordFormControl = new FormControl('', [
    Validators.required,
  ]);

  matcher = new MyErrorStateMatcher();
  public account = new AccountInput();
  isError: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private accountService: AccountService,
    private customerService: CustomerService
  ) { }

  ngOnInit(): void {
    this.accountService.logout();
  }

  ngAfterContentInit(): void {
    window.scroll(0, 0);
  }
  
  login() {
    let loginInput = new LoginInput(this.account.username, this.account.password);

    console.log(loginInput);
    
    this.accountService.login(loginInput).subscribe(
      data => {
        console.log(data);

        if (data.code == CodeConstants.CODE_SUCCESS) {
          
        }
        else {
          this.isError = true;
        }
      },
      error => {
        this.isError = true;
      })
  }
}

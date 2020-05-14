import { AccountService } from './../../shared/services/account.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CodeConstants } from 'src/app/shared/utils/code.constants';
import { Constant } from 'src/app/shared/utils/constant';
import { CustomerService } from 'src/app/shared/services/customer.service';
import { UrlConstants } from 'src/app/shared/utils/url.constants';
import { AccountInput } from '../../models/account-input';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';

/* Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  date = new FormControl(new Date());
  serializedDate = new FormControl((new Date()).toISOString());

  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  passwordFormControl = new FormControl('', [
    Validators.required,
  ]);

  fistNameFormControl = new FormControl('', [
    Validators.required,
  ]);

  lastNameFormControl = new FormControl('', [
    Validators.required,
  ]);

  phoneFormControl = new FormControl('', [
    Validators.required,
  ]);

  userNameFormControl = new FormControl('', [
    Validators.required,
  ]);

  matcher = new MyErrorStateMatcher();
  public account = new AccountInput();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private accountService: AccountService
    ) { }

  ngOnInit(): void {

  }

  ngAfterContentInit(): void {
    window.scroll(0, 0);
  }

  _register(){
    this.accountService._register(this.account).subscribe((data)=>{
      if(data['code'] == 200) this.router.navigateByUrl('/dang-nhap');
      else window.scroll(0, 0);
    });
  }

}

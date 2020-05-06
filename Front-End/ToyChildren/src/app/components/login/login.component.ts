import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountService } from 'src/app/shared/services/account.service';
import { CodeConstants } from 'src/app/shared/utils/code.constants';
import { Constant } from 'src/app/shared/utils/constant';
import { CustomerService } from 'src/app/shared/services/customer.service';
import { UrlConstants } from 'src/app/shared/utils/url.constants';
import { AccountInput } from 'src/app/models/account-input';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [AccountService]
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  public account: AccountInput;
  isError: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private accountService: AccountService,
    private customerService: CustomerService
  ) { }

  ngOnInit(): void {

  }

  login() {
    this.accountService.login(this.username, this.password).subscribe(
      data => {
        if (data.code == CodeConstants.CODE_SUCCESS) {
          this.getUserLogged();
          this.router.navigateByUrl(UrlConstants.HOME_URL);
        }
        else {
          this.isError = true;
        }
      },
      error => {
        this.isError = true;
      })
  }

  inputChange(){

  }

  _register(){
    // this.api._register(this.account).subscribe((data)=>{
    //   console.log(data);
    //   //if(data['code'] == 200) this.router.navigateByUrl('/trang-chu');
    // });
  }

  getUserLogged() {
    this.customerService.getCustomer().subscribe(
      data => {
        console.log(data);
        
        sessionStorage.setItem(Constant.USER_SESSION, JSON.stringify(data.data));
      },
      error => {
        // hanld error
      }
    )
  }
}

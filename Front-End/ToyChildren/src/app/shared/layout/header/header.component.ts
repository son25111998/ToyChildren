import { Component, OnInit, Input } from '@angular/core';
import { CategoryService } from 'src/app/shared/services/category.service';
import { Cart } from 'src/app/models/cart.model';
import { CartService } from 'src/app/shared/services/cart.service';
import { Category } from 'src/app/models/category';
import { Customer } from 'src/app/models/customer';
import { AccountService } from '../../services/account.service';
import { Constant } from '../../utils/constant';
import { Router } from '@angular/router';
import { UrlConstants } from '../../utils/url.constants';
import { CodeConstants } from '../../utils/code.constants';
import { SharingDataService } from '../../services/sharing-data.service';

declare var $: any;

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers: [CategoryService, CartService, AccountService]
})
export class HeaderComponent implements OnInit {
  categories = new Array<Category>();
  carts = new Array<Cart>();
  customer: Customer;
  isLogged: boolean = false;

  constructor(
    private categoryService: CategoryService,
    private cartService: CartService,
    private accountService: AccountService,
    private router: Router,
    private sharingDate: SharingDataService
  ) { }

  ngOnInit(): void {
    this.loadCategory();
    this.loadCustomer();
    this.loadCart();
  }

  loadCategory() {
    return this.categoryService.getCategory().subscribe(data => {
      this.categories = data.data;
    })
  }

  loadCart() {
    this.sharingDate.currentCarts.subscribe(data => this.carts = data);
  }

  loadCustomer() {
    this.customer = JSON.parse(sessionStorage.getItem(Constant.USER_SESSION));
    console.log(this.customer);

    if (this.customer == null) {
      this.isLogged = false;
    } else {
      this.isLogged = true;
    }
  }

  logout() {
    this.accountService.logout();
    this.router.navigateByUrl(UrlConstants.LOGIN_URL);  
  }

  ngAfterViewInit(): void {
    $(document).ready(function () {
      $(window).scroll(function (event) {
        var st = $(this).scrollTop();
        if (st > 100) {
          $('.mod-header').attr('style', 'position: fixed;');
        } else {
          $('.mod-header').removeAttr('style');
        }
      });
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { CategoryService } from 'src/app/shared/services/category.service';
import { Cart } from 'src/app/models/cart.model';
import { CartService } from 'src/app/shared/services/cart.service';
import { Category } from 'src/app/models/category';
import { AccountService } from '../../services/account.service';
import { Router } from '@angular/router';
import { UrlConstants } from '../../utils/url.constants';
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
  username: string;
  isLogged: boolean = false;

  constructor(
    private categoryService: CategoryService,
    private accountService: AccountService,
    private router: Router,
    private sharingDate: SharingDataService
  ) { }

  ngOnInit(): void {
    this.loadCategory();
    this.loadInfoLogged();
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

  loadInfoLogged() {
    this.sharingDate.currentUsername.subscribe(
      data => this.username = data
    );

    this.sharingDate.currentUsername.subscribe(
      data => {
        if (data == null) {
          this.isLogged = false;
        } else {
          this.isLogged = true;
        }
      }
    );
  }

  logout() {
    this.accountService.logout();
    this.sharingDate.changeLogged(null,null);
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

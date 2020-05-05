import { Component, OnInit, NgZone } from '@angular/core';
import { Cart } from 'src/app/models/cart.model';
import { CartService } from 'src/app/shared/services/cart.service';
import { Router } from '@angular/router';
import { CodeConstants } from 'src/app/shared/utils/code.constants';
import { SharingDataService } from 'src/app/shared/services/sharing-data.service';
import { Constant } from 'src/app/shared/utils/constant';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  carts = new Array<Cart>();
  totalMoney: number;

  constructor(
    private ngZone: NgZone,
    private router: Router,
    private cartService: CartService,
    private sharingDate: SharingDataService
  ) { }

  ngOnInit() {
    this.loadCart();
    this.getTotalMoney();
  }

  ngAfterContentInit(): void {
    window.scroll(0, 0);
  }
  
  deleteProductOutCart(cart: Cart) {
    let response = this.cartService.deleteProductOutCart(cart);
    if (response.code == CodeConstants.CODE_SUCCESS) {
      this.loadCart();
    } else {
      alert("Thất bại");
    }
  }

  plusQuantity(cart: Cart) {
    if (cart.product.amount > cart.quantity) {
      let response = this.cartService.updateCart(cart, true);
      console.log(response);
      
      if (response.code == CodeConstants.CODE_SUCCESS) {
        this.loadCart();
      } else {
        alert("Cập nhật thất bại");
      }
    }
  }

  minusQuantity(cart: Cart) {
    if (cart.quantity > 1) {
      let response = this.cartService.updateCart(cart, false);
      if (response.code == CodeConstants.CODE_SUCCESS) {
        this.loadCart();
      } else {
        alert("Cập nhật thất bại");
      }
    }
  }

  getTotalMoney(): number {
    if (this.carts == null) return 0;
    this.totalMoney = 0;
    this.carts.forEach(cart => {
      this.totalMoney += (cart.product.price) * cart.quantity;
    })
    return this.totalMoney;
  }

  loadCart(){
    this.sharingDate.changeCarts(JSON.parse(sessionStorage.getItem(Constant.CART_SESSION)));
    this.carts = JSON.parse(sessionStorage.getItem(Constant.CART_SESSION));
    this.getTotalMoney();
  }
}


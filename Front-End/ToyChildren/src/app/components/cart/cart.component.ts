import { Component, OnInit, NgZone } from '@angular/core';
import { Cart } from 'src/app/models/cart.model';
import { CartService } from 'src/app/shared/services/cart.service';
import { Router } from '@angular/router';
import { DataResponse } from 'src/app/models/data-response';
import { CodeConstants } from 'src/app/shared/utils/code.constants';

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
    private cartService: CartService
  ) { }

  ngOnInit() {
    this.loadCart();
    this.getTotalMoney();
  }

  loadCart() {
    this.carts = this.cartService.getCart();
  }

  deleteProductOutCart(cart: Cart) {
    let response = this.cartService.deleteProductOutCart(cart);
    if (response.code == CodeConstants.CODE_SUCCESS) {
      alert("Xoá thành công");
      location.reload();
    } else {
      alert("Thất bại");
    }
  }

  plusQuantity(cart: Cart) {
    if (cart.product.amount > cart.quantity) {
      let response = this.cartService.updateCart(cart, true);
      if (response.code == CodeConstants.CODE_SUCCESS) {
        alert("Cập nhật thành công");
        location.reload();
      } else {
        alert("Cập nhật thất bại");
      }
    }
  }

  minusQuantity(cart: Cart) {
    if (cart.quantity > 1) {
      let response = this.cartService.updateCart(cart, false);
      if (response.code == CodeConstants.CODE_SUCCESS) {
        alert("Cập nhật thành công");
        location.reload();
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
}


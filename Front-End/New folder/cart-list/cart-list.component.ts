import { Component, OnInit, NgZone } from '@angular/core';
import { Cart } from 'src/app/models/cart.model';
import { CartService } from 'src/app/shared/services/cart.service';
import { Router } from '@angular/router';
import { DataResponse } from 'src/app/models/data-response';

@Component({
  selector: 'app-cart-list',
  templateUrl: './cart-list.component.html',
  styleUrls: ['./cart-list.component.css']
})
export class CartListComponent implements OnInit {
  carts = new Array<Cart>();
  totalMoney: number;
  totalProduct: number = 0;

  constructor(
    private ngZone: NgZone,
    private router: Router,
    private cartService: CartService
  ) { }

  ngOnInit() {
    this.loadCart();
  }

  loadCart() {
    this.carts = this.cartService.getCart();
  }

  deleteProductOutCart(id: number) {
    
  }

  plusQuantity(cartId: number) {
    this.carts.forEach(cart => {
      if (cart.cartId == cartId && cart.product.amount > cart.quantity) {
        cart.quantity += 1;
      }
    })
    this.getTotalMoney();
  }

  minusQuantity(cartId: number) {
    this.carts.forEach(cart => {
      if (cart.cartId == cartId && cart.quantity > 1) {
        cart.quantity -= 1;
      }
    })
    this.getTotalMoney();
  }

  getTotalMoney(): number {
    this.totalMoney = 0;
    this.carts.forEach(cart => {
      this.totalMoney += (cart.product.price) * cart.quantity;
    })
    return this.totalMoney;
  }
}

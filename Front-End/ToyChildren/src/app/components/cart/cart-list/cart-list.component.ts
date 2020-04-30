import { Component, OnInit, NgZone } from '@angular/core';
import { Cart } from 'src/app/models/cart.model';
import { CartService } from 'src/app/shared/services/cart.service';
import { Router } from '@angular/router';
import { DataResponse } from 'src/app/models/data-response';
import { UrlConstants } from 'src/app/shared/utils/url.constants';

@Component({
  selector: 'app-cart-list',
  templateUrl: './cart-list.component.html',
  styleUrls: ['./cart-list.component.css']
})
export class CartListComponent implements OnInit {

  carts = new Array<Cart>();
  totalMoney: number = 0;
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
    return this.cartService.getCart().subscribe((data: DataResponse<Cart[]>) => {
      this.carts = data.data;
      this.totalProduct = this.carts.length;
      this.totalMoney = this.getTotalMoney();
    })
  }

  deleteProductOutCart(id: number) {
    return this.cartService.deleteProductOutCart(id).subscribe((data: DataResponse<Object>) => {
      this.loadCart();
    })
  }

  plusQuantity(cartId: number) {
    this.carts.forEach(cart => {
      if (cart.cartId == cartId && cart.product.amount > cart.quantity) {
        cart.quantity += 1;
      }
    })
  }

  minusQuantity(cartId: number) {

  }

  getTotalMoney(): number {
    this.carts.forEach(cart => {
      this.totalMoney += (cart.product.price) * cart.quantity;
    })
    return this.totalMoney;
  }
}

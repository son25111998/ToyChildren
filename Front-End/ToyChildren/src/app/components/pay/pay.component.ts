import { Component, OnInit, NgZone } from '@angular/core';
import { PayService } from 'src/app/shared/services/pay.service';
import { Router } from '@angular/router';
import { Cart } from 'src/app/models/cart.model';
import { CartService } from 'src/app/shared/services/cart.service';
import { FormsModule, NgForm } from '@angular/forms';
import { PayInput } from 'src/app/models/pay-input';
import { Coupon } from 'src/app/models/coupon';
import { CouponService } from 'src/app/shared/services/coupon.service';
import { CodeConstants } from 'src/app/shared/utils/code.constants';
import { ShippingService } from 'src/app/shared/services/shipping.service';
import { Shipping } from 'src/app/models/shipping';
import { UrlConstants } from 'src/app/shared/utils/url.constants';
import { FormatMoneyPipe } from 'src/app/shared/pipes/format-money-pipe';
import { MessageConstants } from 'src/app/shared/utils/message.constants';
import { Constant } from 'src/app/shared/utils/constant';

@Component({
  selector: 'app-pay',
  templateUrl: './pay.component.html',
  styleUrls: ['./pay.component.css'],
  providers: [PayService, FormsModule, CouponService, ShippingService, FormatMoneyPipe]
})
export class PayComponent implements OnInit {
  carts = new Array<Cart>();
  shippings = new Array<Shipping>();
  payInput: PayInput;
  coupon: Coupon;
  shipping: Shipping;
  couponCode: string;
  totalMoney: number;
  totalProductMoney: number;
  existCoupon: boolean = false;
  errCoupon: boolean = false;


  constructor(
    private router: Router,
    private payService: PayService,
    private cartService: CartService,
    private counponService: CouponService,
    private shippingService: ShippingService
  ) { }

  ngOnInit(): void {
    this.loadCarts();
    this.loadShipping();
    this.payInput = new PayInput(1, 1, 1, "Thu tiền tận nơi",this.carts);
  }

  onSubmit() {
    this.payInput.carts = this.carts;
    console.log("data: " + JSON.stringify(this.payInput));
    sessionStorage.removeItem(Constant.CART_SESSION);
    this.payService.pay(this.payInput).subscribe(
      data => {
        window.scroll(0, 0);
        if (data.code == CodeConstants.CODE_FORBIDDEN) {
          alert(MessageConstants.NOT_LOGIN);
          this.router.navigateByUrl(UrlConstants.LOGIN_URL);
        }
        alert(data.massage);
        if (data.code == CodeConstants.CODE_SUCCESS) {
          sessionStorage.removeItem(Constant.CART_SESSION);
          this.router.navigateByUrl(UrlConstants.HOME_URL);
        }
      },
      error => {
        if (error.code == CodeConstants.CODE_FORBIDDEN) {
          alert(MessageConstants.NOT_LOGIN);
          this.router.navigateByUrl(UrlConstants.LOGIN_URL);
        } else {
          alert(error.massage);
        }
      }
    );
  }

  getCoupon() {
    if (this.couponCode != null) {
      return this.counponService.getCouponByCode(this.couponCode).subscribe(data => {
        if (data.code == CodeConstants.CODE_SUCCESS) {
          this.coupon = data.data;
          this.payInput.couponId = this.coupon.id;
          this.errCoupon = false;
        }
        else {
          this.coupon = null;
          this.errCoupon = true;
        }
        this.getTotalMoney();
      })
    }
  }

  loadShipping() {
    return this.shippingService.getShipping().subscribe(data => {
      this.shippings = data.data;
      this.shipping = this.shippings[0];
      this.getTotalMoney();
    })

  }

  loadCarts() {
    this.totalProductMoney = 0;
    this.carts = this.cartService.getCart();

    this.carts.forEach(cart => {
      this.totalProductMoney += cart.product.price * (1 - cart.product.discount / 100) * cart.quantity;
    });
  }

  SelectShipping(shipping: Shipping) {
    this.shipping = shipping;
    this.getTotalMoney();
  }

  getTotalMoney() {
    this.totalMoney = this.totalProductMoney;

    if (this.shipping != null) {
      this.totalMoney += this.shipping.cost;
    }

    if (this.coupon != null) {
      this.totalMoney -= this.coupon.sale;
    }
  }
}

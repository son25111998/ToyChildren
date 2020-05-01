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

@Component({
  selector: 'app-pay',
  templateUrl: './pay.component.html',
  styleUrls: ['./pay.component.css'],
  providers: [PayService, FormsModule, CouponService, ShippingService]
})
export class PayComponent implements OnInit {
  carts = new Array<Cart>();
  shippings = new Array<Shipping>();
  payInput: PayInput;
  coupon: Coupon;
  shipping: Shipping;
  couponCode: string;
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
    this.payInput = new PayInput(1, 1, 1, "Thu tiền tận nơi");
    this.loadCarts();
    this.loadShipping();
  }

  onSubmit() {
    console.log("data: " + this.payInput.couponId + " - " + this.payInput.shippingId + " - " + this.payInput.payment);

    this.payService.pay(this.payInput).subscribe(data => {
      console.log(this.payInput);
      alert(data.massage);
      this.router.navigateByUrl(UrlConstants.HOME_URL);
    });
  }

  getCoupon() {
    if (this.couponCode != null) {
      return this.counponService.getCouponByCode(this.couponCode).subscribe(data => {
        if (data.code == CodeConstants.CODE_SUCCESS) {
          this.coupon = data.data;
          this.payInput.couponId = this.coupon.id;
        }
        else {
          this.errCoupon = true;
        }
      })
    }
  }

  loadShipping() {
    return this.shippingService.getShipping().subscribe(data => {
      this.shippings = data.data;
      this.shipping = this.shippings[0];
    })
  }

  loadCarts() {
    return this.cartService.getCart().subscribe(data => {
      this.carts = data.data;
    })
  }

  SelectShipping(shipping: Shipping) {
    this.shipping = shipping;
  }
}

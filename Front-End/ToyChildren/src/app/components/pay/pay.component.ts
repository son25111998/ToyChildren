import { Component, OnInit } from '@angular/core';
import { PayService } from 'src/app/shared/services/pay.service';
import { Router } from '@angular/router';
import { Cart } from 'src/app/models/cart.model';
import { FormsModule } from '@angular/forms';
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
import { SharingDataService } from 'src/app/shared/services/sharing-data.service';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogModel, ConfirmDialogComponent } from 'src/app/shared/layout/confirm-dialog/confirm-dialog.component';
import { MomoRequest } from 'src/app/models/momo-request';

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
  momoRequest: MomoRequest;

  constructor(
    private router: Router,
    public dialog: MatDialog,
    private payService: PayService,
    private counponService: CouponService,
    private shippingService: ShippingService,
    private sharingDate: SharingDataService
  ) { }

  ngOnInit(): void {
    this.loadCarts();
    this.loadShipping();
    this.payInput = new PayInput(1, 1, 1, 1, this.carts);
  }

  ngAfterContentInit(): void {
    window.scroll(0, 0);
  }

  onSubmit() {
    this.payInput.carts = this.carts;
    this.payInput.sumMoney = this.totalMoney;
    console.log(this.payInput);
    
    this.payService.pay(this.payInput).subscribe(
      data => {
        window.scroll(0, 0);
        if (data.code == CodeConstants.CODE_FORBIDDEN) {
          alert(MessageConstants.NOT_LOGIN);
          this.router.navigateByUrl(UrlConstants.LOGIN_URL);
        }
        if (data.code == CodeConstants.CODE_SUCCESS) {
          sessionStorage.removeItem(Constant.CART_SESSION);
          this.loadCarts();
          if(data.message === "Momo"){
            // this.getMomoRequest(data.data.id.toString(),this.totalMoney.toString());
            
            this.router.navigate(['/hoa-don'], {queryParams: {id: data.data.id}});
          }else{
            this.confirmDialog("BABY SHOP","Thanh toán thành công đơn hàng","Về trang chủ",null,UrlConstants.HOME_URL,null);
          }
        }
      },
      error => {
        if (error.code == CodeConstants.CODE_FORBIDDEN) {
          this.confirmDialog("BABY SHOP",MessageConstants.NOT_LOGIN,"Đăng nhập",null,UrlConstants.LOGIN_URL,null);
        } else {
          this.confirmDialog("BABY SHOP",error.massage,"OK",null,null,null);
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
    this.carts = JSON.parse(sessionStorage.getItem(Constant.CART_SESSION));
    if (this.carts != null) {
      this.carts.forEach(cart => {
        this.totalProductMoney += cart.product.price * (1 - cart.product.discount / 100) * cart.quantity;
      });
    }
    this.sharingDate.changeCarts(this.carts);
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

  getMomoRequest(orderId: string, sumMoney: string){
    this.momoService.getMomoRequest(orderId, sumMoney).subscribe(
      data => {
        this.router.navigateByUrl(data.data);
      },
      error => {

      }
    )
  }

  getStrRequest(momoRequest: MomoRequest): string{
    return 
  }

  confirmDialog(title: string, message: string, textYes: string, textNo: string, urlYes: string, urlNo: string) {

    const dialogData = new ConfirmDialogModel(title, message,textYes,textNo,urlYes,urlNo);

    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      maxWidth: "500px",
      data: dialogData
    });
  }
}

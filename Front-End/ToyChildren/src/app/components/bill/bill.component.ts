import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from 'src/app/models/order';
import { OrderService } from 'src/app/shared/services/order.service';
import { OrderDetail } from 'src/app/models/order-detail';
import { ConfirmDialogModel, ConfirmDialogComponent } from 'src/app/shared/layout/confirm-dialog/confirm-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { CodeConstants } from 'src/app/shared/utils/code.constants';
import { UrlConstants } from 'src/app/shared/utils/url.constants';
import { Constant } from 'src/app/shared/utils/constant';
import { MessageConstants } from 'src/app/shared/utils/message.constants';

@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.css']
})
export class BillComponent implements OnInit {
  order: Order;
  orderDetails: Array<OrderDetail>;
  orderId: number;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private dialog: MatDialog,
    private orderService: OrderService
  ) { }

  ngOnInit(): void {
    this.checkLogin();
    this.getOrder();
  }

  getOrder(){
    this.orderId = this.route.snapshot.params['id'];
    this.orderService.getOrderById(this.orderId).subscribe(
      data =>{
        this.order = data.data;
        this.orderDetails = this.order.orderDetails;
      },
      error => {

      }
    )
  }

  checkLogin(){
    if(localStorage.getItem(Constant.TOKEN) == null){
      window.scroll(0, 0);
      this.confirmDialog("BABY SHOP",MessageConstants.NOT_LOGIN,"Đăng nhập",null,UrlConstants.LOGIN_URL,null);
    }
  }

  confirm(){
    this.orderService.updateStatusOrder(this.orderId,2).subscribe(
      data =>{
        if(data.code == CodeConstants.CODE_SUCCESS){
          this.confirmDialog("BABY SHOP","Xác nhận thanh toán thành công đơn hàng","Về trang chủ",null,UrlConstants.HOME_URL,null)
        }
        
      },
      erroe => {

      }
    )
  }

  confirmDialog(title: string, message: string, textYes: string, textNo: string, urlYes: string, urlNo: string) {

    const dialogData = new ConfirmDialogModel(title, message,textYes,textNo,urlYes,urlNo);

    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      maxWidth: "500px",
      data: dialogData
    });
  }

}

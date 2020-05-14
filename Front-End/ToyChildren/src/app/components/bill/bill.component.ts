import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from 'src/app/models/order';
import { OrderService } from 'src/app/shared/services/order.service';
import { OrderDetail } from 'src/app/models/order-detail';

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
    private orderService: OrderService
  ) { }

  ngOnInit(): void {
    
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

  confirm(){
    this.orderService.updateStatusOrder(this.orderId,2).subscribe(
      data =>{
        this.router.navigateByUrl("trang-chu");
      },
      erroe => {

      }
    )
  }

}

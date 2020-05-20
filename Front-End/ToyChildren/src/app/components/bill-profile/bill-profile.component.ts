import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/shared/services/order.service';
import { Order } from 'src/app/models/order';
import { Constant } from 'src/app/shared/utils/constant';
import { CodeConstants } from 'src/app/shared/utils/code.constants';

@Component({
  selector: 'app-bill-profile',
  templateUrl: './bill-profile.component.html',
  styleUrls: ['./bill-profile.component.css']
})

export class BillProfileComponent implements OnInit {
  orders = new Array<Order>();
  constructor(private orderService : OrderService) { }

  ngOnInit(): void {
    this.loadOrder();
  }

  loadOrder(){
    this.orderService.getOrderByProfile().subscribe(data => {
      this.orders = data.data;
    })
  }

  closeOrder(id: number){
    this.orderService.updateStatusOrder(id,0).subscribe(data => {
      if(data.code == CodeConstants.CODE_SUCCESS){
        this.loadOrder();
      }
    })
  }

}

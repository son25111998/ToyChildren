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

  carts: Array<Cart> = new Array<Cart>();


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
    })
  }

  deleteProductOutCart(id: number) {
    return this.cartService.deleteProductOutCart(id).subscribe(res => {
      this.ngZone.run(() => this.router.navigateByUrl('/gio-hang'))
    })
  }

  plusQuantity(cartId: number){
    this.carts.forEach(cart => {
      if(cart.cartId == cartId){
        
      }
    })
  }

  minusQuantity(cartId: number){
    
  }
}

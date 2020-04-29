import { Component, OnInit, NgZone } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { CartService } from 'src/app/shared/services/cart.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart-add',
  templateUrl: './cart-add.component.html',
  styleUrls: ['./cart-add.component.css']
})
export class CartAddComponent implements OnInit {
  cartInput: FormGroup;

  constructor(
    public fb: FormBuilder,
    private ngZone: NgZone,
    private router: Router,
    private cartService: CartService
  ) { }

  ngOnInit(): void {
      this.addCart();
  }

  addCart(){
    this.cartInput = this.fb.group({
      productId: '',
      quantity: ''
    })
  }

  submitForm() {
    this.cartService.addCart(this.cartInput.value).subscribe(res => {
      this.ngZone.run(() => this.router.navigateByUrl('/gio-hang'))
    });
  }
}

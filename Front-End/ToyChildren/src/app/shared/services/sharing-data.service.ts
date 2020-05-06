import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Cart } from 'src/app/models/cart.model';
import { Constant } from '../utils/constant';

@Injectable({
  providedIn: 'root'
})
export class SharingDataService {
  carts = new BehaviorSubject<Cart[]>([]);
  currentCarts = this.carts.asObservable();

  constructor() { }

  changeCarts(carts: Cart[]) {
    this.carts.next(carts);
  }
}

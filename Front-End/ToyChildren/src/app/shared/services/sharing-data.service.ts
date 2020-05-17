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

  token = new BehaviorSubject<string>(localStorage.getItem(Constant.TOKEN));
  currentToken = this.token.asObservable();

  username = new BehaviorSubject<string>(localStorage.getItem(Constant.USER_NAME));
  currentUsername = this.username.asObservable();

  constructor() { }

  changeCarts(carts: Cart[]) {
    this.carts.next(carts);
  }

  changeLogged(token: string, username: string){
    this.token.next(token);
    this.username.next(username);
  }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError, Subject } from 'rxjs';
import { Cart } from '../../models/cart.model';
import { UrlConstants } from '../utils/url.constants';
import { catchError, retry } from 'rxjs/operators';
import { CartInput } from '../../models/cart-input';
import { DataResponse } from 'src/app/models/data-response';
import { Constant } from '../utils/constant';
import { CodeConstants } from '../utils/code.constants';
import { MessageConstants } from '../utils/message.constants';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  constructor(private http: HttpClient) { }

  public getCart(): Cart[] {
    return JSON.parse(sessionStorage.getItem(Constant.CART_SESSION));
  }

  public addCart(cartInput: CartInput): DataResponse<Cart> {
    let response = new DataResponse<Cart>();
    try {
      let carts: Cart[] = JSON.parse(sessionStorage.getItem(Constant.CART_SESSION));
      let cart = new Cart();
      let isExists = false;
      let index = 0;

      carts = this.getCart();

      if (cartInput.product == null) {

      }

      if (carts == null || carts.length == 0) {
        carts = new Array<Cart>();
        cart.cartId = 1;
      } else {
        for (let i = 0; i < carts.length; i++) {
          if (carts[i].product.id == cartInput.product.id) {
            isExists = true;
            cart = carts[i];
            index = i;
            carts.splice(i, 1);
            break;
          }
        }

        if (!isExists)
          cart.cartId = carts[(carts.length - 1)].cartId + 1;
      }

      if (isExists) {
        if(cart.quantity < cart.product.amount){
          cart.quantity = cart.quantity + cartInput.quantity;
        }
        carts.splice(index, 0, cart);
      } else {
        cart.product = cartInput.product;
        cart.quantity = cartInput.quantity;
        carts.push(cart);
      }

      sessionStorage.setItem(Constant.CART_SESSION, JSON.stringify(carts));

      this.setResponseSuccess(response,cart);
    } catch (error) {
      this.setResponseError(response,error);
    }
    return response;
  }

  public updateCart(cart: Cart, check: boolean): DataResponse<Cart> {
    let response = new DataResponse<Cart>();
    try {
      let carts = this.getCart();
      let index = 0;

      for (let i = 0; i < carts.length; i++) {

        if (cart.cartId == carts[i].cartId) {
          if (check) {
            carts[i].quantity += 1;
          } else {
            carts[i].quantity -= 1;
          }
          index = i;
          sessionStorage.setItem(Constant.CART_SESSION, JSON.stringify(carts));
          break;
        }
      }

      this.setResponseSuccess(response,carts[index]);
    } catch (err) {
      this.setResponseSuccess(response,err);
    }
    return response;
  }

  public deleteProductOutCart(cart: Cart): DataResponse<Cart> {
    let response = new  DataResponse<Cart>();
    try {
      let carts = this.getCart();
      console.log(carts);
      
      for (let i = 0; i < carts.length; i++) {
        if (cart.cartId == carts[i].cartId) {
          carts.splice(i, 1);
          break;
        }
      }
      sessionStorage.setItem(Constant.CART_SESSION, JSON.stringify(carts));
      this.setResponseSuccess(response,cart);
    } catch (err) {
      this.setResponseError(response,err);
    }

    console.log(response);
    return response;
  }

  private setResponseError(response: DataResponse<any>, error: string){
    response.code = CodeConstants.CODE_UNKNOW;
    response.message = error;
  }

  private setResponseSuccess(response: DataResponse<any>,data: any){
      response.data = data;
      response.code = CodeConstants.CODE_SUCCESS;
      response.message = MessageConstants.MESSAGE_SUCCESS;
  }
}

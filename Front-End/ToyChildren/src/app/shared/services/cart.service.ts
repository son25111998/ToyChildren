import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Cart } from '../../models/cart.model';
import { UrlConstants } from '../utils/url.constants';
import { catchError, retry } from 'rxjs/operators';
import { CartInput } from '../../models/cart-input';
import { DataResponse } from 'src/app/models/data-response';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  // Http Headers
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  public getCart(): Observable<DataResponse<Cart[]>> {
    return this.http.get<DataResponse<Cart[]>>(UrlConstants.BASE_URL + '/api/v1/cart')
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  public addCart(cartInput: CartInput): Observable<DataResponse<Cart>> {
    return this.http.post<DataResponse<Cart>>(UrlConstants.BASE_URL + '/api/v1/cart', JSON.stringify(cartInput), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  public updateCart(id: number): Observable<DataResponse<Cart>> {
    return this.http.put<DataResponse<Cart>>(UrlConstants.BASE_URL + '/api/v1/cart/' + id, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  public deleteProductOutCart(id: number): Observable<DataResponse<Cart>> {
    return this.http.delete<DataResponse<Cart>>(UrlConstants.BASE_URL + '/api/v1/cart/' + id)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  errorHandl(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(errorMessage);
  }
}

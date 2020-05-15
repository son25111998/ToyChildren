import { Injectable } from '@angular/core';
import { DataResponse } from 'src/app/models/data-response';
import { throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Order } from 'src/app/models/order';
import { UrlConstants } from '../utils/url.constants';
import { stringify } from 'querystring';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) {  }

  public getOrderById(id: number){
    return this.http.get<DataResponse<Order>>(UrlConstants.ORDER_API_URL + id)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  public updateStatusOrder(id: number,status: number){
    const params = new HttpParams().set('status', status.toString());
    
    return this.http.put<DataResponse<Object>>(UrlConstants.ORDER_API_URL + id + "/" + status,null)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  public getMoneyByMonth(){
    return this.http.get<DataResponse<string[]>>(UrlConstants.STATISTIC_API_URL)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  errorHandl(error: any) {
    let response = new DataResponse<Object>();
    
    // client-side error
    if (error.error instanceof ErrorEvent) {
      response.code = error.error.code;
      response.message = error.error.message;
    } 
    // server-side error
    else {
      response.code = error.status;
      response.message = error.message;
    }
    response.data = null;
    return throwError(response);
  }
}

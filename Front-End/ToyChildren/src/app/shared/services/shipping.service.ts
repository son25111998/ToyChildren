import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { DataResponse } from 'src/app/models/data-response';
import { UrlConstants } from '../utils/url.constants';
import { catchError, retry } from 'rxjs/operators';
import { Shipping } from 'src/app/models/shipping';

@Injectable({
  providedIn: 'root'
})
export class ShippingService {

  constructor(private http: HttpClient) { }

  getShipping(): Observable<DataResponse<Shipping[]>> {
    return this.http.get<DataResponse<Shipping[]>>(UrlConstants.SHIPPING_API_URL)
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
      response.massage = error.error.message;
    } 
    // server-side error
    else {
      response.code = error.status;
      response.massage = error.message;
    }
    response.data = null;
    return throwError(response);
  }
}

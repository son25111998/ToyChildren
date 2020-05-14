import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UrlConstants } from '../utils/url.constants';
import { Observable, throwError } from 'rxjs';
import { Coupon } from 'src/app/models/coupon';
import { DataResponse } from 'src/app/models/data-response';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CouponService {

  constructor(private http: HttpClient) { }

  getCouponByCode(code: string): Observable<DataResponse<Coupon>> {
    return this.http.get<DataResponse<Coupon>>(UrlConstants.COUPON_API_URL + '?code=' + code)
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

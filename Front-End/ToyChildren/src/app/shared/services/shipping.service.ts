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

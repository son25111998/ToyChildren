import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { DataResponse } from 'src/app/models/data-response';
import { Customer } from 'src/app/models/customer';
import { UrlConstants } from '../utils/url.constants';
import { retry, catchError } from 'rxjs/operators';
import { CodeConstants } from '../utils/code.constants';
import { Constant } from '../utils/constant';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  constructor(private http: HttpClient) { }

  getCustomer(): Observable<DataResponse<Customer>> {
    const headers = new HttpHeaders({
      Authorization: sessionStorage.getItem(Constant.HEADERS_SESSION)
    })

    return this.http.get<DataResponse<Customer>>(UrlConstants.CUSTOMER_API_URL)
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

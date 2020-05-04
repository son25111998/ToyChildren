import { Customer } from '../../models/customer';
import { Account } from '../../models/account';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DataResponse } from '../../models/data-response';
import { UrlConstants } from '../utils/url.constants';
import { ProductOutput } from '../../models/product-output';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  // Http Headers
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  _login(email:string,password:string): Observable<string> {
    return this.http.get<string>(UrlConstants.BASE_URL + '/api/v1/account/login?email='+email+'&password='+password, this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }

  _register(account: Object): Observable<Object> {
    return this.http.post(UrlConstants.BASE_URL + '/api/v1/account/register',JSON.stringify(account), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }

 // Error handling
  errorHandl(error) {
     let errorMessage = '';
     if(error.error instanceof ErrorEvent) {
       // Get client-side error
       errorMessage = error.error.message;
     } else {
       // Get server-side error
       errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
     }
     console.log(errorMessage);
     return throwError(errorMessage);
  }
}

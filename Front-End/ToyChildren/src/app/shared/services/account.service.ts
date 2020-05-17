import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UrlConstants } from '../utils/url.constants';
import { DataResponse } from 'src/app/models/data-response';
import { retry, catchError } from 'rxjs/operators';
import { throwError, Observable } from 'rxjs';
import { Constant } from '../utils/constant';
import { Customer } from 'src/app/models/customer';
import { LoginInput } from 'src/app/models/login-input';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  login(loginInput: LoginInput) {
    return this.http.post<DataResponse<string>>(UrlConstants.AUTH_API_URL, loginInput, this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }

  public _register(account: Object): Observable<DataResponse<Customer>> {
    return this.http.post<DataResponse<Customer>>(UrlConstants.REGISTER_API_URL, JSON.stringify(account), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  public logout() {
    window.localStorage.clear();
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

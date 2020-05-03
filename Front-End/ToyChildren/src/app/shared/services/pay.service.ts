import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PayInput } from 'src/app/models/pay-input';
import { DataResponse } from 'src/app/models/data-response';
import { UrlConstants } from '../utils/url.constants';
import { throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PayService {

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }
  
  public pay(pay: PayInput){
    return this.http.post<DataResponse<Object>>(UrlConstants.PAY_API_URL, JSON.stringify(pay), this.httpOptions)
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

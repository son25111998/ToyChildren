import { Category } from 'src/app/models/category';
import { UrlConstants } from '../utils/url.constants';
import { ProductOutput } from '../../models/product-output';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DataResponse } from '../../models/data-response';

@Injectable({
  providedIn: 'root'
})
export class ProductListService {

  constructor(private http: HttpClient) { }

  // Http Headers
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  // get list product
  getProducts(page,size): Observable<DataResponse<ProductOutput>> {
    return this.http.get<DataResponse<ProductOutput>>(UrlConstants.BASE_URL + '/api/v1/product/list?page='+page+'&size='+size)
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }

  // get list category
  public getCategory(): Observable<DataResponse<Category>> {
    return this.http.get<DataResponse<Category>>(UrlConstants.BASE_URL + '/api/v1/category')
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

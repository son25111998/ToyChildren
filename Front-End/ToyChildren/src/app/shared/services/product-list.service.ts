import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DataResponse } from '../../models/data-response';
import { Category } from 'src/app/models/category';
import { ProductOutput } from 'src/app/models/product-output';
import { UrlConstants } from '../utils/url.constants';

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
  getProducts(page : number, size: number): Observable<DataResponse<ProductOutput>> {
    return this.http.get<DataResponse<ProductOutput>>(UrlConstants.PRODUCT_API_URL + 'list?page=' + page + '&size=' + size)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  // get list category
  public getCategory(): Observable<DataResponse<Category[]>> {
    return this.http.get<DataResponse<Category[]>>(UrlConstants.CATEGORY_API_URL)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  // Error handling
  errorHandl(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
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

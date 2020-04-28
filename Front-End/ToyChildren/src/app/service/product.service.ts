import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { UrlConstants } from '../shared/utils/url.constants';
import { ProductOutput } from '../models/product-output.model';
import { retry, catchError } from 'rxjs/operators';
import { DataResponse } from '../models/response.model';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  // Http Headers
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  public getProductByCategory(): Observable<ProductOutput> {
    return this.http.get<ProductOutput>(UrlConstants.URL + '/api/v1/category')
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  public findById(id: number): Observable<DataResponse<Product>> {
    return this.http.get<DataResponse<Product>>(UrlConstants.URL + '/api/v1/product/info/' + id)
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

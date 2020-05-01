import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { UrlConstants } from '../utils/url.constants';
import { retry, catchError } from 'rxjs/operators';
import { ProductOutput } from 'src/app/models/product-output';
import { DataResponse } from 'src/app/models/data-response';
import { Product } from 'src/app/models/product';
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

   // get list product
   getProducts(page,size): Observable<DataResponse<ProductOutput>> {
    return this.http.get<DataResponse<ProductOutput>>(UrlConstants.PRODUCT_API_URL + 'list?page='+page+'&size='+size)
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }

  // get list product new
  getProductNews(page,size): Observable<DataResponse<ProductOutput>> {
    return this.http.get<DataResponse<ProductOutput>>(UrlConstants.PRODUCT_API_URL + 'new?page='+page+'&size='+size)
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }

  public getProductByCategory(): Observable<ProductOutput> {
    return this.http.get<ProductOutput>(UrlConstants.CATEGORY_API_URL)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      )
  }

  public findById(id: number): Observable<DataResponse<Product>> {
    return this.http.get<DataResponse<Product>>(UrlConstants.PRODUCT_API_URL + 'info/' + id)
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

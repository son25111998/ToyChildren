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

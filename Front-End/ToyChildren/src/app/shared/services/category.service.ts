import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { UrlConstants } from '../utils/url.constants';
import { retry, catchError } from 'rxjs/operators';
import { DataResponse } from 'src/app/models/data-response';
import { Category } from 'src/app/models/category';
import { ProductOutput } from 'src/app/models/product-output';

@Injectable()
export class CategoryService{

  constructor(private http: HttpClient) {}

  // Http Headers
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  public getCategory(): Observable<DataResponse<Category>> {
    return this.http.get<DataResponse<Category>>(UrlConstants.BASE_URL + '/api/v1/category')
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }

  public findAllProductByCategory(id: number, page: number, size: number): Observable<DataResponse<ProductOutput>> {
    return this.http.get<DataResponse<ProductOutput>>(UrlConstants.BASE_URL + '/api/v1/category/' + id+ '?page=' +page+'&size=' + size)
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }

  errorHandl(error) {
    let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(errorMessage);
 }
}

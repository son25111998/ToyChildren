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

  public getCategory(): Observable<DataResponse<Category[]>> {
    return this.http.get<DataResponse<Category[]>>(UrlConstants.CATEGORY_API_URL)
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }

  public findAllProductByCategory(id: number, page: number, size: number): Observable<DataResponse<ProductOutput>> {
    return this.http.get<DataResponse<ProductOutput>>(UrlConstants.CATEGORY_API_URL + id+ '?page=' +page+'&size=' + size)
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

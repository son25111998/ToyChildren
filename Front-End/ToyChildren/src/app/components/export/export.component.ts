import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UrlConstants } from 'src/app/shared/utils/url.constants';

@Component({
  selector: 'app-export',
  templateUrl: './export.component.html',
  styleUrls: ['./export.component.css']
})
export class ExportComponent implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    
  }

  export(){
    this.http.get(UrlConstants.EXPORT_FILE_API_URL, {responseType: 'blob' }).subscribe(
      data =>{
        var blob = new Blob([data], { type: 'application/vnd.ms-excel' });
        
          window.navigator.msSaveOrOpenBlob(blob, "ThongKe.xlsx");
      }  
    )
  }
}

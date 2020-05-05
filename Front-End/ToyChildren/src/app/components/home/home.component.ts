import { Product } from './../../models/product';
import { Component, OnInit } from '@angular/core';
import { HomeService } from '../../shared/services/home-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [HomeService]
})
export class HomeComponent implements OnInit {

  public productNews: Array<Product>;
  public productFeatured: Array<Product>;
  public maxTotalRecord: boolean = false;
  public totalRecord: number = 0;
  private size: number = 8;

  constructor(private api: HomeService) {

  }

  ngOnInit(): void {

    // get product new
    this.api.getProductNews(1,4).subscribe((data)=>{
      this.productNews = data.data.products;
    });

    // get product featured
    this.api.getProducts(1,8).subscribe((data)=>{
      this.productFeatured = data.data.products;
      this.totalRecord = data.data.pagination.totalRecord - 8;
    });

  }

  loadMoreProduct() {
    // get product featured
    if(this.totalRecord <= 8){
      this.api.getProducts(1,this.size + this.totalRecord).subscribe((data)=>{
        this.productFeatured = data.data.products;
        this.maxTotalRecord = true;
        this.totalRecord = 0;
      });
    } else {
      this.size+=8;
      this.api.getProducts(1,this.size).subscribe((data)=>{
        this.productFeatured = data.data.products;
        this.totalRecord -= 8;
      });
    }
  }
}

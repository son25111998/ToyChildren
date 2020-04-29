import { Product } from './../../models/product';
import { Component, OnInit } from '@angular/core';
import { HomeService } from '../../shared/services/Home/home-service.service';


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
  private size: number = 8;

  constructor(private api: HomeService) {

  }

  ngOnInit(): void {

    // get product new
    this.api.getProductNews(1,4).subscribe((data)=>{
      this.productNews = data.data.products;
      console.log(data.data.products);
    });

    // get product featured
    this.api.getProducts(1,8).subscribe((data)=>{
      this.productFeatured = data.data.products;
      console.log(data.data.products);
    });

  }

  loadMoreProduct(size: number) {
    this.size+=size;
    // get product featured
    this.api.getProducts(1,this.size).subscribe((data)=>{
      this.productFeatured = data.data.products;
      if(this.size >= data.data.pagination.totalRecord) {
        this.maxTotalRecord = true;
      }
    });

  }
}

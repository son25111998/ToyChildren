import { Component, OnInit } from '@angular/core';
import { ProductListService } from 'src/app/shared/services/product-list.service';
import { Product } from 'src/app/models/product';
import { Category } from 'src/app/models/category';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  public products: Array<Product>;
  public categorys: Array<Category>;
  public maxTotalRecord: boolean = false;
  public totalRecord: number = 0;
  private size: number = 8;
  constructor(private api: ProductListService) { }

  ngOnInit(): void {
    // get products
    this.api.getProducts(1, 8).subscribe((data) => {
      this.products = data.data.products;
      this.totalRecord = data.data.pagination.totalRecord - 8;
      console.log(data.data.products);
    });

    // get list category
    this.api.getCategory().subscribe((data) => {
      this.categorys = data.data;
      console.log(this.categorys);
    });
  }

  loadMoreProduct() {
    // get product featured
    if (this.totalRecord <= 8) {
      this.api.getProducts(1, this.size + this.totalRecord).subscribe((data) => {
        this.products = data.data.products;
        this.maxTotalRecord = true;
        this.totalRecord = 0;
      });
    } else {
      this.size += 8;
      this.api.getProducts(1, this.size).subscribe((data) => {
        this.products = data.data.products;
        this.totalRecord -= 8;
      });
    }
  }
}

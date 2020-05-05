import { CategoryService } from './../../shared/services/category.service';
import { Category } from 'src/app/models/category';
import { ProductListService } from './../../shared/services/product-list.service';
import { Product } from 'src/app/models/product';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
})
export class ProductListComponent implements OnInit {
  public products: Array<Product>;
  public categories: Array<Category>;
  public maxTotalRecord: boolean = false;
  public totalRecord: number = 0;
  private size: number = 8;
  public selectedCategory: number;
  private categoryId: number;
  private priceIndex: number;
  public prices: Array<string>;

  constructor(
    private api: ProductListService,
    private route: ActivatedRoute,
    private categoryService: CategoryService
  ) {}

  ngOnInit(): void {
    // add item to select-box prices
    this.prices = [
      '0 - 500',
      '500 - 1 triệu',
      '1 triệu - 2 triệu',
      '2 triệu - 3 triệu',
      'Trên 3 triệu',
    ];

    this.route.paramMap.subscribe((params) => {
      this.size = 8;
      this.maxTotalRecord = false;
      this.categoryId = Number(params.get('id'));
      this.getProducts(this.categoryId);
    });

    // load list category
    this.categoryService.getCategory().subscribe((data: {}) => {
      this.categories = data['data'];
    });
  }

  getProducts(categoryId: number) {
    if (categoryId == undefined || categoryId == null || categoryId == 0) {
      // get products
      this.api.getProducts(1, 8).subscribe((data) => {
        this.products = data.data.products;
        this.totalRecord =
          data.data.pagination.totalRecord >= 8
            ? data.data.pagination.totalRecord - 8
            : 0;
        if (this.totalRecord == 0) this.maxTotalRecord = true;
      });
    } else {
      this.selectedCategory = categoryId;
      this.categoryService
        .findAllProductByCategory(categoryId, 1, 8)
        .subscribe((data) => {
          this.products = data.data.products;
          this.totalRecord =
            data.data.pagination.totalRecord >= 8
              ? data.data.pagination.totalRecord - 8
              : 0;
          if (this.totalRecord == 0) this.maxTotalRecord = true;
        });
    }
  }

  loadMoreProduct() {
    // get product featured
    if (this.totalRecord <= 8) {
      if (this.categoryId != undefined) {
        this.categoryService
          .findAllProductByCategory(
            this.categoryId,
            1,
            this.size + this.totalRecord
          )
          .subscribe((data) => {
            this.products = data.data.products;
          });
      } else {
        this.api
          .getProducts(1, this.size + this.totalRecord)
          .subscribe((data) => {
            this.products = data.data.products;
          });
      }
      this.maxTotalRecord = true;
      this.totalRecord = 0;
    } else {
      this.size += 8;
      if (this.categoryId != undefined) {
        this.categoryService
          .findAllProductByCategory(this.categoryId, 1, this.size)
          .subscribe((data) => {
            this.products = data.data.products;
          });
      } else {
        this.api.getProducts(1, this.size).subscribe((data) => {
          this.products = data.data.products;
        });
      }
      this.totalRecord -= 8;
    }
  }

  changePrice(priceIndex: number) {
    this.priceIndex = priceIndex;
    console.log(priceIndex);
  }
}

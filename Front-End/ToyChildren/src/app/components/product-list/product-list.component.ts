import { ProductListInput } from '../../models/product-list-input';
import { CategoryService } from './../../shared/services/category.service';
import { Category } from 'src/app/models/category';
import { ProductListService } from './../../shared/services/product-list.service';
import { Product } from 'src/app/models/product';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SharingDataService } from 'src/app/shared/services/sharing-data.service';
import { Constant } from 'src/app/shared/utils/constant';
import { CodeConstants } from 'src/app/shared/utils/code.constants';
import { CartService } from 'src/app/shared/services/cart.service';
import { DataResponse } from 'src/app/models/data-response';
import { Cart } from 'src/app/models/cart.model';
import { CartInput } from 'src/app/models/cart-input';

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
  private categoryId: number;
  public priceIndex: number;
  public prices: Array<string>;
  public productListInput = new ProductListInput();
  private productListInput2 = new ProductListInput();
  private check: boolean = true;
  cartInput = new CartInput();

  constructor(
    private api: ProductListService,
    private route: ActivatedRoute,
    private categoryService: CategoryService,
    private cartService: CartService,
    private sharingDate: SharingDataService
  ) {}

  ngOnInit(): void {
    // add item to select-box prices
    this.prices = [
      '0 - 500 nghìn',
      '500 nghìn - 1 triệu',
      '1 triệu - 2 triệu',
      '2 triệu - 3 triệu',
      'Trên 3 triệu',
    ];

    this.productListInput.page = 1;
    this.productListInput.size = 8;
    this.productListInput.search = '';

    this.route.paramMap.subscribe((params) => {
      this.maxTotalRecord = false;
      if (!isNaN(parseInt(params.get('id'))))
        this.productListInput.categoryId = parseInt(params.get('id'));
      this.getProducts(this.productListInput);
    });

    // load list category
    this.categoryService.getCategory().subscribe((data: {}) => {
      this.categories = data['data'];
    });
  }

  ngAfterContentInit(): void {
    window.scroll(0, 0);
  }

  getProducts(input: ProductListInput) {
    this.api.filterProducts(input).subscribe((data) => {
      this.products = data.data.products;
      if (this.check) {
        this.totalRecord =
          data.data.pagination.totalRecord >= 8
            ? data.data.pagination.totalRecord - 8
            : 0;
      }
      if (this.totalRecord == 0) this.maxTotalRecord = true;
    });
  }

  loadMoreProduct() {
    this.check = false;
    if (this.totalRecord <= 8) {
      this.productListInput.size += this.totalRecord;
      this.totalRecord = 0;
      this.maxTotalRecord = true;
    } else {
      this.productListInput.size += 8;
      this.totalRecord -= 8;
    }
    this.getProducts(this.productListInput);
  }

  changePrice(priceIndex: number) {
    this.maxTotalRecord = false;
    this.check = true;
    this.productListInput.size = 8;
    this.priceIndex = priceIndex;
    if (this.priceIndex == 0) {
      this.productListInput.priceStart = 0;
      this.productListInput.priceEnd = 500000;
    } else if (this.priceIndex == 1) {
      this.productListInput.priceStart = 500000;
      this.productListInput.priceEnd = 1000000;
    } else if (this.priceIndex == 2) {
      this.productListInput.priceStart = 1000000;
      this.productListInput.priceEnd = 2000000;
    } else if (this.priceIndex == 3) {
      this.productListInput.priceStart = 2000000;
      this.productListInput.priceEnd = 3000000;
    } else if (this.priceIndex == 4) {
      this.productListInput.priceStart = 3000000;
    } else {
      this.productListInput2 = new ProductListInput();
      if (this.productListInput.categoryId != undefined)
        this.productListInput2.categoryId = this.productListInput.categoryId;
      this.productListInput2.search = this.productListInput.search;
      this.productListInput2.page = this.productListInput.page;
      this.productListInput2.size = 8;
      this.productListInput = this.productListInput2;
    }

    this.getProducts(this.productListInput);
  }

changeSearch(val: any){
  this.maxTotalRecord = false;
  this.check = true;
  this.productListInput.size = 8;
  this.productListInput.search = val;
  this.getProducts(this.productListInput);
}

addToCart(product: Product) {
  let response = new DataResponse<Cart>();

  this.cartInput.product = product;
  this.cartInput.quantity = 1;

  response = this.cartService.addCart(this.cartInput);

  if (response.code == CodeConstants.CODE_SUCCESS) {
    this.loadCart();
  } else {
    alert("Thêm không thành công sản phẩm vào giỏ hàng");
  }
}

loadCart() {
  this.sharingDate.changeCarts(JSON.parse(sessionStorage.getItem(Constant.CART_SESSION)));
}

  // changeCategory(categoryId: number) {
  //   this.maxTotalRecord = false;
  //   this.check = true;
  //   this.productListInput.categoryId = categoryId;
  //   if (categoryId == undefined) {
  //     this.productListInput2 = new ProductListInput();
  //     this.productListInput2.search = this.productListInput.search;
  //     this.productListInput2.page = this.productListInput.page;
  //     this.productListInput2.size = 8;
  //     if (this.productListInput.priceStart != undefined) {
  //       this.productListInput2.priceStart = this.productListInput.priceStart;
  //     }
  //     if (this.productListInput.priceEnd) {
  //       this.productListInput2.priceEnd = this.productListInput.priceEnd;
  //     }
  //     this.productListInput = this.productListInput2;
  //   }

  //   this.getProducts(this.productListInput);
  // }
}

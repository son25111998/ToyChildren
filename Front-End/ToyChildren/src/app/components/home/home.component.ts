import { Product } from './../../models/product';
import { Component, OnInit, NgZone, Output, EventEmitter } from '@angular/core';
import { CartInput } from 'src/app/models/cart-input';
import { CartService } from 'src/app/shared/services/cart.service';
import { CodeConstants } from 'src/app/shared/utils/code.constants';
import { Router } from '@angular/router';
import { UrlConstants } from 'src/app/shared/utils/url.constants';
import { ProductService } from 'src/app/shared/services/product.service';
import { DataResponse } from 'src/app/models/data-response';
import { Cart } from 'src/app/models/cart.model';
import { FormatMoneyPipe } from 'src/app/shared/pipes/format-money-pipe';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [ProductService, CartService, FormatMoneyPipe]
})
export class HomeComponent implements OnInit {
  public productNews: Array<Product>;
  public productFeatured: Array<Product>;
  public maxTotalRecord: boolean = false;
  private size: number = 8;
  private cartInput = new CartInput();

  constructor(
    private api: ProductService,
    private cartService: CartService,
    private router: Router,
    private ngZone: NgZone,
  ) { }

  ngOnInit(): void {

    // get product new
    this.api.getProductNews(1, 4).subscribe((data) => {
      this.productNews = data.data.products;
    });

    // get product featured
    this.api.getProducts(1, 8).subscribe((data) => {
      this.productFeatured = data.data.products;
    });

  }

  loadMoreProduct(size: number) {
    this.size += size;
    // get product featured
    this.api.getProducts(1, this.size).subscribe((data) => {
      this.productFeatured = data.data.products;
      if (this.size >= data.data.pagination.totalRecord) {
        this.maxTotalRecord = true;
      }
    });

  }

  addToCart(product: Product) {
    let response = new DataResponse<Cart>();
    
    this.cartInput.product = product;
    this.cartInput.quantity = 1;

    response = this.cartService.addCart(this.cartInput);
    if (response.code == CodeConstants.CODE_SUCCESS) {
      alert("Thêm thành công sản phẩm vào giỏ hàng");
    } else {
      alert("Thêm không thành công sản phẩm vào giỏ hàng");
    }
    this.router.navigateByUrl(UrlConstants.CART_URL);
  }
}

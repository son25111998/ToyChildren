import { Product } from '../../models/product';
import { Component, OnInit, NgZone, Output, EventEmitter } from '@angular/core';
import { CartInput } from 'src/app/models/cart-input';
import { CartService } from 'src/app/shared/services/cart.service';
import { CodeConstants } from 'src/app/shared/utils/code.constants';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/shared/services/product.service';
import { DataResponse } from 'src/app/models/data-response';
import { Cart } from 'src/app/models/cart.model';
import { FormatMoneyPipe } from 'src/app/shared/pipes/format-money-pipe';
import { SharingDataService } from 'src/app/shared/services/sharing-data.service';
import { Constant } from 'src/app/shared/utils/constant';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [ProductService, CartService, FormatMoneyPipe]
})
export class HomeComponent implements OnInit {
  public productNews: Array<Product>;
  public productFeatured: Array<Product>;
  private cartInput = new CartInput();
  public maxTotalRecord: boolean = false;
  private size: number = 8;
  public totalRecord: number = 0;

  constructor(
    private api: ProductService,
    private cartService: CartService,
    private router: Router,
    private ngZone: NgZone,
    private sharingDate: SharingDataService
  ) { }

  ngOnInit(): void {

    // get product new
    this.api.getProductNews(1, 4).subscribe((data) => {
      this.productNews = data.data.products;
    });

    // get product featured
    this.api.getProducts(1, 8).subscribe((data) => {
      this.productFeatured = data.data.products;
      this.totalRecord = data.data.pagination.totalRecord - 8;
    });

    this.loadCart();
  }

  loadMoreProduct() {
    // get product featured
    if (this.totalRecord <= 8) {
      this.api.getProducts(1, this.size + this.totalRecord).subscribe((data) => {
        this.productFeatured = data.data.products;
        this.maxTotalRecord = true;
        this.totalRecord = 0;
      });
    } else {
      this.size += 8;
      this.api.getProducts(1, this.size).subscribe((data) => {
        this.productFeatured = data.data.products;
        this.totalRecord -= 8;
      });
    }
  }

  addToCart(product: Product) {
    let response = new DataResponse<Cart>();

    this.cartInput.product = product;
    this.cartInput.quantity = 1;

    response = this.cartService.addCart(this.cartInput);
    if (response.code == CodeConstants.CODE_SUCCESS) {
      this.loadCart();
    } else {

    }
  }

  loadCart(){
    this.sharingDate.changeCarts(JSON.parse(sessionStorage.getItem(Constant.CART_SESSION)));
  }
}

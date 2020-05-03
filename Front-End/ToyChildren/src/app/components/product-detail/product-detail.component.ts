import { Component, OnInit, NgZone } from '@angular/core';
import { ProductService } from 'src/app/shared/services/product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from 'src/app/shared/services/category.service';
import { CartInput } from 'src/app/models/cart-input';
import { CartService } from 'src/app/shared/services/cart.service';
import { CodeConstants } from 'src/app/shared/utils/code.constants';
import { Product } from 'src/app/models/product';
import { UrlConstants } from 'src/app/shared/utils/url.constants';
import { Constant } from 'src/app/shared/utils/constant';
import { Cart } from 'src/app/models/cart.model';
import { DataResponse } from 'src/app/models/data-response';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css'],
  providers: [ProductService, CategoryService, CartService]
})
export class ProductDetailComponent implements OnInit {
  products = new Array<Product>();
  carts = new Array<Cart>();
  product = new Product();
  id: number;
  quantity: number = 1;
  maxQuantity: number;
  cartInput = new CartInput();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private productService: ProductService,
    private categoryService: CategoryService,
    private cartService: CartService
  ) { }

  ngOnInit(): void {
    this.loadProduct();
  }

  loadProduct() {
    this.id = this.route.snapshot.params['id'];
    this.productService.findById(this.id).subscribe(
      data => {
        this.product = data.data;
        this.maxQuantity = this.product.amount;
        this.loadListProduct(this.product.category.id);
      },
      error => {
        // handl error
      }
    )
  }

  loadListProduct(id: number) {
    this.categoryService.findAllProductByCategory(id, 1, 8).subscribe(
      data => {
        this.products = data.data.products;
      },
      error => {
        // handl error
      }
    )
  }

  plusProduct() {
    if (this.maxQuantity > this.quantity)
      this.quantity += 1;
  }

  minusProduct() {
    if (this.quantity > 1)
      this.quantity -= 1;
  }

  onSubmit() {
    let response = new DataResponse<Cart>();

    if (this.quantity > this.product.amount) {
      this.cartInput.quantity = 1;
    }

    this.cartInput.product = this.product;
    this.cartInput.quantity = this.quantity;

    response = this.cartService.addCart(this.cartInput);
    if (response.code == CodeConstants.CODE_SUCCESS) {
      alert("Thêm thành công sản phẩm vào giỏ hàng");
      window.scroll(0,0);
    } else {
      alert("Thêm không thành công sản phẩm vào giỏ hàng");
    }
    this.router.navigateByUrl(UrlConstants.CART_URL);
  }

  addToCart(product: Product) {
    let response = new DataResponse<Cart>();

    this.cartInput.product = product;
    this.cartInput.quantity = 1;

    response = this.cartService.addCart(this.cartInput);

    if (response.code == CodeConstants.CODE_SUCCESS) {
      alert("Thêm thành công sản phẩm vào giỏ hàng");
      window.scroll(0,0);
    } else {
      alert("Thêm không thành công sản phẩm vào giỏ hàng");
    }
    this.router.navigateByUrl(UrlConstants.CART_URL);
  }

  productDetail(product: Product) {
    window.scroll(0,0);
    this.product = product;
    this.router.navigateByUrl(UrlConstants.PRODUCT_URL + product.id);
  }
}

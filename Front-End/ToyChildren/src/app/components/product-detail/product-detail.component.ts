import { Component, OnInit, NgZone } from '@angular/core';
import { ProductService } from 'src/app/shared/services/product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from 'src/app/shared/services/category.service';
import { CartInput } from 'src/app/models/cart-input';
import { CartService } from 'src/app/shared/services/cart.service';
import { CodeConstants } from 'src/app/shared/utils/code.constants';
import { Product } from 'src/app/models/product';
import { UrlConstants } from 'src/app/shared/utils/url.constants';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css'],
  providers: [ProductService]
})
export class ProductDetailComponent implements OnInit {
  products = new Array<Product>();
  product = new Product();
  id: number;
  quantity: number = 1;
  maxQuantity: number;
  cartInput = new CartInput();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private ngZone: NgZone,
    private productService: ProductService,
    private categoryService: CategoryService,
    private cartService: CartService
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.productService.findById(this.id).subscribe(data => {
      this.product = data.data;
      this.categoryService.findAllProductByCategory(this.product.category.id, 1, 8).subscribe(data => {
        this.products = data.data.products;
      })
      this.maxQuantity = this.product.amount;
    })
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
    if (this.quantity > this.product.amount) {
      this.cartInput.quantity = 1;
    }

    this.cartInput.productId = this.id;
    this.cartInput.quantity = this.quantity;
    this.cartService.addCart(this.cartInput).subscribe(data => {
      if (data.code == CodeConstants.CODE_SUCCESS) {
        alert("Thêm thành công sản phẩm vào giỏ hàng");
      } else {
        alert("Thêm không thành công sản phẩm vào giỏ hàng");
      }
      this.ngZone.run(() => this.router.navigateByUrl(UrlConstants.CART_URL))
    })
  }

  addToCart(id: number) {
    this.cartInput.productId = id;
    this.cartInput.quantity = 1;
    this.cartService.addCart(this.cartInput).subscribe(data => {
      if (data.code == CodeConstants.CODE_SUCCESS) {
        alert("Thêm thành công sản phẩm vào giỏ hàng");
      } else {
        alert("Thêm không thành công sản phẩm vào giỏ hàng");
      }
      this.ngZone.run(() => this.router.navigateByUrl(UrlConstants.CART_URL))
    })
  }
}

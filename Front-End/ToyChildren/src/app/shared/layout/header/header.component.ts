import { Component, OnInit } from '@angular/core';
import { CategoryService } from 'src/app/shared/services/category.service';
import { Cart } from 'src/app/models/cart.model';
import { CartService } from 'src/app/shared/services/cart.service';
import { Category } from 'src/app/models/category';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers: [CategoryService, CartService]
})
export class HeaderComponent implements OnInit {
  categories = new Array<Category>();
  carts = new Array<Cart>();
  totalItem: number = 0;

  constructor(private categoryService: CategoryService, private cartService: CartService) { }

  ngOnInit(): void {
    this.loadCategory();
    this.loadCart();
  }

  loadCategory() {
    return this.categoryService.getCategory().subscribe(data => {
      this.categories = data.data;
    })
  }

  loadCart() {
    return this.cartService.getCart().subscribe(data => {
      this.carts = data.data;
      if (this.carts != null)
        this.totalItem = this.carts.length;
    })
  }
}

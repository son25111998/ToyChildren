import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/models/category.model';
import { CategoryService } from 'src/app/service/category.service';
import { Observable } from 'rxjs';
import { Cart } from 'src/app/models/cart.model';
import { CartService } from 'src/app/service/cart.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  categories: any = [];
  carts: Array<Cart>;

  constructor(private categoryService: CategoryService, private cartService: CartService) { }

  ngOnInit(): void {
    this.loadCategory();
    this.loadCart();
  }

  loadCategory() {
    return this.categoryService.getCategory().subscribe((data: {}) => {
      this.categories = data['data'];
    })
  }

  loadCart(){
    
  }
}

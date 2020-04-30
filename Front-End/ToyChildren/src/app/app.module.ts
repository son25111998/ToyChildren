import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from "@angular/forms";
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { CategoryComponent } from './components/category/category.component';
import { ProductDetailComponent } from './components/product-detail/product-detail.component';
import { OrderComponent } from './components/order/order.component';
import { PayComponent } from './components/pay/pay.component';
import { HeaderComponent } from './shared/layout/header/header.component';
import { FooterComponent } from './shared/layout/footer/footer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from "@angular/router";
import { routes } from './app-routing.module';
import { CartAddComponent } from './components/cart/cart-add/cart-add.component';
import { CartListComponent } from './components/cart/cart-list/cart-list.component';
import { CategoryService } from './shared/services/category.service';
import { CartService } from './shared/services/cart.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    CategoryComponent,
    ProductDetailComponent,
    OrderComponent,
    PayComponent,
    HeaderComponent,
    FooterComponent,
    CartAddComponent,
    CartListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [CategoryService, CartService],
  bootstrap: [AppComponent],
})
export class AppModule {}

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
import { PayComponent } from './components/pay/pay.component';
import { HeaderComponent } from './shared/layout/header/header.component';
import { FooterComponent } from './shared/layout/footer/footer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from "@angular/router";
import { routes } from './app-routing.module';
import { CategoryService } from './shared/services/category.service';
import { CartService } from './shared/services/cart.service';
import { FormatMoneyPipe } from './shared/pipes/format-money-pipe';
import { CartComponent } from './components/cart/cart.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    CategoryComponent,
    ProductDetailComponent,
    PayComponent,
    HeaderComponent,
    FooterComponent,
    FormatMoneyPipe,
    CartComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [CategoryService, CartService, FormatMoneyPipe],
  bootstrap: [AppComponent],
})
export class AppModule {}

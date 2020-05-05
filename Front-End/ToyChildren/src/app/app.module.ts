import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from "@angular/forms";
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
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
import { ProductListComponent } from './components/product-list/product-list.component';
import { ConfirmDialogComponent } from './shared/layout/confirm-dialog/confirm-dialog.component';
import { CustomMaterialModule } from './shared/layout/confirm-dialog/custom-material.module';
import { MatSelectModule } from '@angular/material/select';
import {TextFieldModule} from '@angular/cdk/text-field';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { ExportComponent } from './components/export/export.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    ProductDetailComponent,
    PayComponent,
    HeaderComponent,
    FooterComponent,
    FormatMoneyPipe,
    CartComponent,
    ProductListComponent,
    ConfirmDialogComponent,
    ExportComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    CustomMaterialModule,
    FormsModule,
    RouterModule.forRoot(routes),
    MatSelectModule,
    TextFieldModule,
    MDBBootstrapModule.forRoot(),
  ],
  providers: [CategoryService, CartService, FormatMoneyPipe, ConfirmDialogComponent],
  bootstrap: [AppComponent],
})
export class AppModule { }


import { NgModule } from '@angular/core';
import { Routes, RouterModule } from "@angular/router";
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ProductDetailComponent } from './components/product-detail/product-detail.component';
import { PayComponent } from './components/pay/pay.component';
import { CartComponent } from './components/cart/cart.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { ExportComponent } from './components/export/export.component';

export const routes: Routes = [
  { path: '', redirectTo: 'trang-chu', pathMatch: 'full' },
  { path: 'trang-chu', component: HomeComponent },
  { path: 'dang-nhap', component: LoginComponent },
  { path: 'san-pham/:id', component: ProductDetailComponent },
  { path: 'gio-hang', component: CartComponent },
  { path: 'thanh-toan', component: PayComponent },
  { path: 'danh-muc', component: ProductListComponent},
  { path: 'danh-muc/:id', component: ProductListComponent},
  { path: 'export', component: ExportComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
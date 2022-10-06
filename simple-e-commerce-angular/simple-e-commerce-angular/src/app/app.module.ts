import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListOrdersComponent } from './list-orders/list-orders.component';
import { ProductsComponent } from './products/products.component';
import { AddProductComponent } from './components/add-product/add-product.component';
import { UpdateProductComponent } from './components/update-product/update-product.component';
import { DeleteProductsComponent } from './components/delete-products/delete-products.component';
import { ViewProductsComponent } from './components/view-products/view-products.component';
import { ViewAllProductsComponent } from './components/view-all-products/view-all-products.component';
import { ViewProductsByDateComponent } from './components/view-products-by-date/view-products-by-date.component';
import { ViewProductsByCategoryComponent } from './components/view-products-by-category/view-products-by-category.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { ListOrderComponent } from './components/list-order/list-order.component';

@NgModule({
  declarations: [
    AppComponent,
    ListOrdersComponent,
    ProductsComponent,
    AddProductComponent,
    UpdateProductComponent,
    DeleteProductsComponent,
    ViewProductsComponent,
    ViewAllProductsComponent,
    ViewProductsByDateComponent,
    ViewProductsByCategoryComponent,
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    ListOrderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

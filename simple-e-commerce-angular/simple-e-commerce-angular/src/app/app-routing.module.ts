import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProductsComponent} from "./products/products.component";
import {AddProductComponent} from "./components/add-product/add-product.component";
import {ListOrderComponent} from "./components/list-order/list-order.component";
import {ViewProductsComponent} from "./components/view-products/view-products.component";
import {ViewAllProductsComponent} from "./components/view-all-products/view-all-products.component";
import {UpdateProductComponent} from "./components/update-product/update-product.component";

const routes: Routes = [
  {path:'',component:ProductsComponent},
  {path:'add-product',component:AddProductComponent},
  {path:'order',component:ListOrderComponent},
  {path:'view-product/:id',component:ViewProductsComponent},
  {path:'list-product',component:ViewAllProductsComponent},
  {path:'update-product/:id',component:UpdateProductComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

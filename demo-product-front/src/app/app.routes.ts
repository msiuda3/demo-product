import { Routes } from '@angular/router';
import { ProductListComponent } from './components/product-list/product-list.component';
import { LoginComponent } from './components/login/login.component';
import { ProductSearchComponent } from './components/product-search/product-search.component';
import { ProductFormComponent } from './components/product-form/product-form.component';


export const routes: Routes = [
  { path: 'products', component: ProductListComponent },
  { path: 'login', component: LoginComponent },
  { path: 'products/search', component: ProductSearchComponent },
  { path: 'products/create', component: ProductFormComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' }, // Default route redirects to login
];

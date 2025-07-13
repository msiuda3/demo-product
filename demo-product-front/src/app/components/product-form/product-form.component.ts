import { Component } from '@angular/core';
import { ProductService, Product } from '../../services/product.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-product-form',
  imports: [FormsModule, CommonModule], 
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss']
})
export class ProductFormComponent {
  product: Product = { name: '', category: '', description: '', price: 0 };
  errors: { [key: string]: string } = {}; 

  constructor(private productService: ProductService, private router: Router) {}

  saveProduct(): void {
    this.productService.createProduct(this.product).subscribe(
      () => {
        this.router.navigate(['/products']);
      },
      (error: HttpErrorResponse) => {
        if (error.status === 400 && error.error) {
          this.errors = error.error;
        } else {
          console.error('Unexpected error:', error);
        }
      }
    );
  }
}

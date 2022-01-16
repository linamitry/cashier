import { Component, OnInit } from '@angular/core';
import {Product} from "../model/product";
import {Category} from "../model/category";
import {ReceiptDto} from "../model/receipt-dto";
import {User} from "../model/user";
import {CategoryService} from "../service/category.service";
import {ProductService} from "../service/product.service";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../service/user.service";
import {CommonService} from "../service/common.service";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  product?: Product[];
  category?: Category[];
  receipt?: ReceiptDto;
  dist?: number[];
  items : number[ ] = [ ];
  categoryName?: string
  user: User = new User();

  constructor(public categoryService: CategoryService,
              public productService: ProductService,
              private route: ActivatedRoute,
              private userService: UserService,
              private commonService: CommonService) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id: number | undefined = Number.parseInt(params['userId']);
      this.userService.getUser(id).subscribe(data => {
        this.user = data.body
      })
      this.categoryService.findAllCategory().subscribe(result => {
        this.category = result
      })
    })
  }

  findProductByCategory(categoryName: string) {
    this.productService.findProductByCategoryUrl(categoryName).subscribe(result => {
      this.product = result
    })
  }

  addProduct(productId: number) {
    this.items.push(productId)
    this.send(this.items)
  }

  sendMessage(): void {
    this.commonService.sendUpdate('Message from Sender Component to Receiver Component!');
  }

  send(items: number[]): void {
    this.commonService.sendProducts(items);
  }

}

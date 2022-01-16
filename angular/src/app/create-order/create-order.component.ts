import {Component, OnInit, VERSION} from '@angular/core';
import {Subscription} from "rxjs";
import {CommonService} from "../service/common.service";
import {Product} from "../model/product";
import {ProductService} from "../service/product.service";
import {FormArray, FormBuilder, FormGroup} from '@angular/forms';
import {ReceiptRequest} from "../model/receipt-request";
import {ReceiptService} from "../service/receipt.service";
import {UserStorageService} from "../service/user-storage.service";
import {ProductWithCount} from "../model/product-with-count";
import {ActivatedRoute} from "@angular/router";
import {ReceiptDto} from "../model/receipt-dto";

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent implements OnInit {
  orderForm?: FormGroup;
  items?: FormArray;
  amount: number = 0;
  receipt: ReceiptRequest = new ReceiptRequest();
  receiptDto: ReceiptDto = new ReceiptDto();
  receiptId?: number;


  isEdit: boolean = false

  private subscriptionName: Subscription;
  ids = new Set();
  drewIds = new Set()


  constructor(private commonService: CommonService,
              public productService: ProductService,
              private formBuilder: FormBuilder,
              private receiptService: ReceiptService,
              private userStorage: UserStorageService,
              private route: ActivatedRoute) {
    this.subscriptionName = this.commonService.getUpdate().subscribe
    (message => {
      message.text.forEach((item: number) => this.ids.add(item))
      this.createItem()
    });
    this.route.queryParams.subscribe(params => {
      this.isEdit = params['isEdit'];
    });
    if (this.isEdit){
      this.route.queryParams.subscribe(params => {
        this.receiptId = params['receiptId']
      });
      this.receiptService.getProductByReceipt(this.receiptId!!).subscribe(
        data => {
          this.receiptDto = data.body

          this.items = this.orderForm!.get('items') as FormArray;
          //@ts-ignore
          data.body.products.forEach((e: Product) => {
            if (!this.drewIds.has(e.id)) {
              let a = this.formBuilder.group({
                name: {value: e.name, disabled: true},
                id: {value: e.id},
                price: {value: e.price, disabled: true},
                price2: {value: e.price},
                count: e.count ? e.count : 1
              })
              this.items!!.push(a);
              this.drewIds.add(e.id)
            }
          })
          this.amountCalc()
        }
      )
    }
  }

  ngOnInit(): void {

      this.orderForm = this.formBuilder.group({
        items: this.formBuilder.array([])
      });
  }

  get userItems() {
    return this.orderForm!!.get('items') as FormArray
  }

  createItem() {
    if (this.isEdit){

    }
      this.items = this.orderForm!.get('items') as FormArray;
      // @ts-ignore
      this.productService.getProductByIds(Array.from(this.ids.values()))
        .subscribe(
          data => {
            data.body.forEach((e: Product) => {
              if (!this.drewIds.has(e.id)) {
                let a = this.formBuilder.group({
                  name: {value: e.name, disabled: true},
                  id: {value: e.id},
                  price: {value: e.price, disabled: true},
                  price2: {value: e.price},
                  count: e.count ? e.count : 1
                })
                this.items!!.push(a);
                this.drewIds.add(e.id)
              }
            })
            this.amountCalc()
          }
        );

  }


  ngOnDestroy() {
    this.subscriptionName.unsubscribe();
  }

  amountCalc() {
    this.amount = this.items!!.getRawValue().reduce((acc: any, cur: any) => acc + cur.price2.value * cur.count, 0)
  }

  onSubmit() {
    console.warn('Your order has been submitted', this.orderForm!!.value);
    //this.checkoutForm.reset();
  }

  createReceipt() {
    this.receipt.cashierId = this.userStorage.getUser().id

    this.receipt.products = this.items?.getRawValue().map(it => {
      let product = new ProductWithCount()
      product.productId = it.id.value
      product.count = it.count
      return product
    })
    this.receiptService.create(this.receipt)
      .subscribe(
        data => {
          console.log(data)
        },
        err => {
          console.log(err)
        }
      );
  }

  updateReceipt(){
    this.receipt.cashierId = this.userStorage.getUser().id

    this.receipt.products = this.items?.getRawValue().map(it => {
      let product = new ProductWithCount()
      product.productId = it.id.value
      product.count = it.count
      return product
    })
    console.log(this.receipt)
    this.receiptService.updateReceipt(this.receiptId!, this.receipt)
      .subscribe(
        data => {
          console.log(data)
        },
        err => {
          console.log(err)
        }
      );
  }
  delete(index: number){
    this.items!!.removeAt(index)
  }
}

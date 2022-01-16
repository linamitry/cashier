import {ReceiptProductUpdate} from "./receipt-product-update";

export class ReceiptUpdate {
  constructor() {
  }

  products?: ReceiptProductUpdate[];
  receiptId?: number;
  amount?: number;
}

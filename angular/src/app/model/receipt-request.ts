import {ProductWithCount} from "./product-with-count";

export class ReceiptRequest {
  constructor() {
  }

  cashierId?: number;
  products?: ProductWithCount[];
}

import { Cart } from './cart.model';

export class PayInput {
    couponId: number;
    taxId: number;
    shippingId: number;
    payment: string;
    carts: Cart[];

    constructor(couponId: number, taxId: number, shippingId: number, payment:string, carts: Cart[]) {
        this.couponId = couponId;
        this.taxId = taxId;
        this.shippingId = shippingId;
        this.payment = payment;
        this.carts = carts;
    }
}

import { Cart } from './cart.model';

export class PayInput {
    couponId: number;
    taxId: number;
    shippingId: number;
    payment: number;
    carts: Cart[];

    constructor(couponId: number, taxId: number, shippingId: number, payment: number, carts: Cart[]) {
        this.couponId = couponId;
        this.taxId = taxId;
        this.shippingId = shippingId;
        this.payment = payment;
        this.carts = carts;
    }
}

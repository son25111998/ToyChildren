export class PayInput {
    couponId: number;
    taxId: number;
    shippingId: number;
    payment: string;

    constructor(couponId: number, taxId: number, shippingId: number, payment:string) {
        this.couponId = couponId;
        this.taxId = taxId;
        this.shippingId = shippingId;
        this.payment = payment;
    }
}

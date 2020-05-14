import { Coupon } from './coupon';
import { Shipping } from './shipping';
import { Customer } from './customer';
import { OrderDetail } from './order-detail';

export class Order {
    id: number;
    createDate: string;
    qrcode: any;
	payment: string;
	status: number;
	coupon: Coupon;
    shipping: Shipping;
	customer: Customer;
	orderDetails: Array<OrderDetail>;
}

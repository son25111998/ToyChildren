import { Account } from './account';

export class Customer {
    id: number;
    firstName: string;
    middleName: string;
    lastName: string;
    gendr: number;
    birthDay: Date;
    phone: string;
    account: Account;
}

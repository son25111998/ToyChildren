import { Category } from './category.model';
import { Manufacturer } from './manufacturer.model';

export class Product {
    id: number;
    name: string;
    description: string;
    thumbai: string;
    price: number;
    amount: number;
    updatedBy: string;
    createdBy: string;
    createTime: string;
    updateTime: string;
    lenght: number;
    width: number;
    height: number;
    status: number;
    discount: number;
    manufacturer: Manufacturer;
    category: Category;
}

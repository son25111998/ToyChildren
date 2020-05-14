import { Manufacturer } from './manufacturer';
import { Category } from './category';
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
  category: Category;
  manufacturer: Manufacturer;
}

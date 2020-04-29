import { Manufacturer } from '../models/manufacturer';
import { Category } from '../models/category';
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

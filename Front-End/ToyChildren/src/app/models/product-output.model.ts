import { Product } from './product.model';
import { Pagination } from './pagination.model';

export class ProductOutput {
    products: Product[];
    pagination: Pagination;
}

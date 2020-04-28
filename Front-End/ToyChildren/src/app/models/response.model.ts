import { Observable } from 'rxjs';

export class DataResponse<T> {
    code: number;
    message: string;
    data: T;
}

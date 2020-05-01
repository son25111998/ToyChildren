import { PipeTransform, Pipe } from '@angular/core';
import { stringify } from 'querystring';

@Pipe({ name: 'currency' })
export class FormatMoneyPipe implements PipeTransform{
    transform(money: number) : string {
        for (let i = Array.from(money.toString()).length - 1; i >= 0 ; i--) {
            const element = Array.from(money.toString())[i];

            
        }
        return money.toString().fixed().replace(/\d(?=(\d{3})+\.)/g, '');
    }
}

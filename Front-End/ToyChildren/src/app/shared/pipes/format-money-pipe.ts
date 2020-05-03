import { PipeTransform, Pipe } from '@angular/core';

@Pipe({ name: 'FomatMoney' })
export class FormatMoneyPipe implements PipeTransform{
    transform(value: number, ...args: any[]) {
        if(value == null){
            return "0₫";
        }
        return value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.')+"₫";
    }
}

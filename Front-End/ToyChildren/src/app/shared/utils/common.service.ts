import { Router } from '@angular/router';

export class CommonService {

    constructor( protected router: Router ) { }

    /**
     * @description Error handling when calling service from server
     * @param error the error infomation
     */
    protected handleError(error: any): Promise<any> {
        debugger
        error = error.json();
        // if stauts error is 403 (Access Denied), re
        if (error.status == 403 || error.status == 401) {
            this.router.navigate(['/login']);
        }
        return Promise.reject(error);
    }
}
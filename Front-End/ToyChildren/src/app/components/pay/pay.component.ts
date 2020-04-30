import { Component, OnInit, NgZone } from '@angular/core';
import { PayInput } from 'src/app/models/pay-input';
import { PayService } from 'src/app/shared/services/pay.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UrlConstants } from 'src/app/shared/utils/url.constants';

@Component({
  selector: 'app-pay',
  templateUrl: './pay.component.html',
  styleUrls: ['./pay.component.css'],
  providers: [PayService, FormBuilder]
})
export class PayComponent implements OnInit {
  payInput: FormGroup;
  couponId: number = 0;

  constructor(
    public fb: FormBuilder,
    private ngZone: NgZone,
    private router: Router,
    private payService: PayService
  ) { }

  ngOnInit(): void {
    this.pay();
  }

  pay() {
    this.payInput = this.fb.group({
      couponId: '',
      shippingId: '',
      payment: '',
      taxId: ''
    })
  }

  onSubmit() {
    this.payService.pay(this.payInput.value).subscribe(res => {
      this.ngZone.run(() => this.router.navigateByUrl(UrlConstants.HOME_URL))
    });
  }

}

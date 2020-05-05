import { Component, OnInit, Input, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.css']
})
export class ConfirmDialogComponent implements OnInit {
  title: string;
  message: string;
  textYes: string;
  textNo: string;
  urlYes: string;
  urlNo: string;

  constructor(public dialogRef: MatDialogRef<ConfirmDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ConfirmDialogModel,
    private router: Router) {
    // Update view with given values
    this.title = data.title;
    this.message = data.message;
    this.textYes = data.textYes;
    this.textNo = data.textNo;
    this.urlYes = data.urlYes;
    this.urlNo = data.urlNo;
  }
  ngOnInit(): void {

  }

  public onConfirm(): void {
    // Close the dialog, return true
    this.dialogRef.close(true);
    if(this.urlYes != null){
      window.scroll(0,0);
      this.router.navigateByUrl(this.urlYes);
    }
  }

  public onDismiss(): void {
    // Close the dialog, return false
    this.dialogRef.close(false);
    if(this.urlNo != null){
      this.router.navigateByUrl(this.urlNo);
    }
  }
}

/**
 * Class to represent confirm dialog model.
 *
 * It has been kept here to keep it as part of shared component.
 */
export class ConfirmDialogModel {

  constructor(
    public title: string,
    public message: string,
    public textYes: string,
    public textNo: string,
    public urlYes: string,
    public urlNo: string
  ) { }
}

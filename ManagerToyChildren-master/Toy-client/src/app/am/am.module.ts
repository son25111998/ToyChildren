import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AmComponent } from './am.component';
import { AmRoutingModule } from './am-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { MdDialogModule, MdButtonModule } from '@angular/material';
// import { AuthRoutingModule } from '../authentication/auth-routing.module';
import { DialogComponent } from './common/dialog/dialog.component';
import { DialogService } from './common/dialog/dialog.service';
import { SidebarComponent } from './common/shared/sidebar/sidebar.component';
 //import { AuthGuard } from '../authentication/guard/auth.guard';
// import { AuthenticationService } from '../authentication/guard/authentication.service';
import { TranslateModule, TranslateLoader, TranslateCompiler, TranslateParser, MissingTranslationHandler } from '@ngx-translate/core';
import { CustomHandler, createTranslateLoader } from '../i18n-setting';
import { HttpClient } from '@angular/common/http';

import { NgIdleKeepaliveModule } from '@ng-idle/keepalive';
import { MomentModule } from 'angular2-moment';
import { AuthGuard } from '../authentication/guard/auth.guard';
import { AuthenticationService } from '../authentication/guard/authentication.service';


@NgModule({
  imports: [
    AmRoutingModule,
    FormsModule,
    HttpModule,
    MdDialogModule,
    MdButtonModule,
    ReactiveFormsModule,
    CommonModule,
    AmRoutingModule,
    TranslateModule.forChild({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [HttpClient]
      },
      // missingTranslationHandler: { provide: MissingTranslationHandler, useClass: CustomHandler },
      // isolate: false
    }),
    TranslateModule,
    MomentModule,
    NgIdleKeepaliveModule.forRoot()
  ],
  entryComponents: [DialogComponent], exports: [DialogComponent],
  providers: [DialogService, AuthGuard, AuthenticationService],
  // providers: [DialogService, AuthGuard],
  // providers: [DialogService],
  declarations: [AmComponent, DialogComponent, SidebarComponent, ]
  //declarations: [AmComponent, DialogComponent, SidebarComponent ]
})
export class AmModule { }

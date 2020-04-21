import { Component, OnInit, AfterViewInit, Input, AfterViewChecked } from '@angular/core';
import { MbsNavigationsService } from './mbs-navigtion.service';
import { MbsNavigation } from './MbsNavigation';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { element } from 'protractor';
import { Constants } from '../../util/constants';
declare var Layout,App: any;
@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
  providers: [MbsNavigationsService]
})
export class SidebarComponent implements OnInit, AfterViewInit,AfterViewChecked {

  menuNavigations: Array<MbsNavigation> = [];
  rootNavations: MbsNavigation[];
  currentUser: any;
  constructor(
    private mbsNavigationsService: MbsNavigationsService,
    private router: Router,
    private translate: TranslateService,
  ) { }

  ngOnInit() {
    debugger;
    // this.getMenuNavigationByCurrentUser();
  }

  ngAfterViewInit() {
    Layout.init();
  }

  setNavigationsName() {
    debugger
    debugger;
    for(let i=0; i<this.menuNavigations.length;i++) {
      debugger
      let navNameTranlate;
      let navNameTranlateKey = 'SIDEBAR.'+this.menuNavigations[i].navname;
      this.translate.get(navNameTranlateKey).subscribe((res: string) => {
          navNameTranlate = res;
          this.menuNavigations[i].navname = navNameTranlate;

      });
    }
  }

  ngAfterViewChecked(){
    //App.init();

  }

  getChildNavigations(parentNavId: number): MbsNavigation[]{
    let childNavigations = [];
    let totalNavs = this.menuNavigations.length;
    for(let i=0; i<totalNavs;i++){
      var item = this.menuNavigations[i];
      if(item.parentNavid == parentNavId){
        childNavigations.push(item);
      }
    }
    return childNavigations;
  }

  getMenuNavigationByCurrentUser(){
    debugger
    try{
      debugger;
      this.currentUser = JSON.parse(localStorage.getItem(Constants.CURRENT_USER));
      var navigations = [];
      navigations = this.currentUser.data.mbsNavigationRoles;
      debugger;
      for(let i=0; i<navigations.length; i++){
        var mbsNavigation = new MbsNavigation();
        mbsNavigation = navigations[i].mbsNavigation;
        this.menuNavigations.push(mbsNavigation);
      }
      this.setNavigationsName();
    }catch(element){
    }
  }
}

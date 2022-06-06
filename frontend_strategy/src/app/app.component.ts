import { Component, ViewChild, OnInit } from '@angular/core';
import { MatDrawer } from '@angular/material';
import { AutenticationService } from './services/autentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  @ViewChild('drawer', { static: false }) private drawer: MatDrawer;
  title = 'Strategy System';
  public opened: boolean = true;

  constructor(public authService: authService) {}

  ngOnInit() {
    this.opened = JSON.parse(sessionStorage.getItem('menu-opened'));
  }

  public toggle() {
    this.drawer.toggle();
    sessionStorage.setItem('menu-opened', this.drawer.opened.toString());
  }
}

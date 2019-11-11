import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthenticationService} from '../service/authentication.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  username: string;

  constructor(private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
   this.authenticationService.currentUser.subscribe(value => this.username = value.username);
  }

  logout() {
    this.authenticationService.logout();
  }


}

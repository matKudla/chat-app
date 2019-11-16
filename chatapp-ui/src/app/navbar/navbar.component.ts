import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthenticationService} from '../service/authentication.service';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  username: string;
  userLoggedIn: boolean;

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.authenticationService.currentUser.subscribe(value => {
      this.refreshNavbar();
    });
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['login']);
  }

  refreshNavbar() {
    if (this.authenticationService.currentUserValue) {
      this.username = this.authenticationService.currentUserValue.username;
      this.userLoggedIn = true;
    } else {
      this.userLoggedIn = false;
    }
  }


}

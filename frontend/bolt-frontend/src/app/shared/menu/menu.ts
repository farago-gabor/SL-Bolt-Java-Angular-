import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-menu',
  imports: [RouterModule],
  templateUrl: './menu.html',
  styleUrl: './menu.scss'
})
export class Menu {

  

  constructor(public authService: AuthService, private router: Router) {}

  get isAdmin() {
    return this.authService.getUser()?.szerepkor === 'admin';
  }

  get isLoggedIn() {
    return this.authService.isLoggedIn();
  }
  
  get isLoggedIn() {
    return this.authService.isLoggedIn();
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}

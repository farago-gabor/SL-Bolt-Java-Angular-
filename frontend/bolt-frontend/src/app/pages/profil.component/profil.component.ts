import { Component, OnInit } from '@angular/core';
import { Dolgozo } from '../../shared/models/dolgozo.model';
import { AuthService } from '../../shared/services/auth.service';

@Component({
  selector: 'app-profil.component',
  imports: [],
  templateUrl: './profil.component.html',
  styleUrl: './profil.component.scss'
})
export class ProfilComponent implements OnInit{

  dolgozo: Dolgozo = new Dolgozo(0 , "", "", ""); // temp

  token: string | null = '';

  constructor(private authService: AuthService) {
    this.token = authService.getToken();
  }

  ngOnInit(): void {
    const user = this.authService.getUser();
    if (user !== null) {
      this.dolgozo = user;
    }
  }

  logout() {
    this.authService.logout();
    window.location.href = '/login';
  }

}

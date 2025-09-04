import { Component } from '@angular/core';
import { AuthService } from '../../shared/services/auth.service';
import { Router } from '@angular/router';
import { Dolgozo } from '../../shared/models/dolgozo.model';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login.component',
  imports: [FormsModule, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  email = '';
  jelszo = '';
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {

  

  }

  onSubmit() {
      this.authService.login(this.email, this.jelszo).subscribe({
        next: (res) => {
          const bejelentkezo = new Dolgozo(res.id, this.email, res.nev, res.szerepkor);
          this.authService.setUser(bejelentkezo);
          this.authService.saveToken(res.token)

          console.log(bejelentkezo);

          this.router.navigate(['/']);
        },

        error: () => {
          this.errorMessage = "Hibás e-mail vagy jelszó!";
        }
      })
    }

}

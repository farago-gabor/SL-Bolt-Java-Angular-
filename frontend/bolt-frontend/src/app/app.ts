import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from './shared/services/auth.service';
import { Menu } from "./shared/menu/menu";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Menu],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('bolt-frontend');

  constructor(private authService: AuthService){
    console.log("Próba");
  }

  ngOnInit(): void {
    this.authService.isLoggedIn();
  }
}

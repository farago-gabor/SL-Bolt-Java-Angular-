import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Dolgozo } from '../models/dolgozo.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private url = 'http://localhost:8080/api/auth';
  private felhasznalo: Dolgozo | null = null;

  constructor(private http: HttpClient) {}

  login(email: string, jelszo: string): Observable<any> {
    return this.http.post(`${this.url}/login`, {email, jelszo});
  }

  saveToken(token: string): void {
    localStorage.setItem('jwt', token);
  }

  getToken(): string | null {
    return localStorage.getItem('jwt');
  }

  setUser(dolgozo: Dolgozo): void {
    this.felhasznalo = dolgozo;
  }
  
  getUser(): Dolgozo | null {
    return this.felhasznalo;
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  logout(): void {
    localStorage.clear();
    this.felhasznalo = null;
  }
  
}

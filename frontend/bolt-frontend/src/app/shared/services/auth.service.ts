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
    localStorage.setItem('felhasznalo', JSON.stringify(dolgozo));
  }
  
  getUser(): Dolgozo | null {
    if (this.felhasznalo) {
    return this.felhasznalo;
    }

    const stored = localStorage.getItem('felhasznalo');
    if (stored) {
      this.felhasznalo = JSON.parse(stored);
      return this.felhasznalo;
    }

    return null;
  }


  // A függvény mostmár figyeli azt is, hogy a token lejárt-e és ha igen, akkor kiveszi localStorage-ből
  isLoggedIn(): boolean {
    const token = this.getToken();
    if (!token) return false;

    // Decodeolja a tokent
    const payload = this.decodeToken(token);
    if (!payload) return false;

    // Lejárt-e?
    const expiryTime = payload.exp;
    if (!expiryTime) return false;

    const currentTime = Math.floor(Date.now() / 1000);
    if (expiryTime < currentTime) {
      this.removeToken(); // Ha leájrt akkor kiveszi
      return false;
    }

    return true;
  }

  decodeToken(token: string): any | null {
    try {
      const payloadBase64 = token.split('.')[1];
      const payloadJson = atob(payloadBase64);
      return JSON.parse(payloadJson);
    } catch (e) {
      return null;
    }
  }

  removeToken() {
    localStorage.removeItem('token');
  }

  logout(): void {
    localStorage.clear();
    this.felhasznalo = null;
  }
  
}

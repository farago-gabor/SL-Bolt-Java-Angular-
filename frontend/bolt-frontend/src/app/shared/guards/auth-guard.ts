import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const authGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);
  const authService = inject(AuthService);

  if(!authService.isLoggedIn()) {
    router.navigate(['/login'])
    return false
  }

  return true;
};

export const adminGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const authService = inject(AuthService);

  // Ha nincs bejelentkezve → login
  if (!authService.isLoggedIn()) {
    router.navigate(['/login']);
    return false;
  }

  // Szerepkör ellenőrzése
  const user = authService.getUser();
  if (!user || user.szerepkor !== 'admin') {
    router.navigate(['/']); // vagy pl. "hozzáférés megtagadva" oldal
    return false;
  }

  return true; // csak az admin mehet tovább
};
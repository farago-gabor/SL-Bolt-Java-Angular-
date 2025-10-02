import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login.component/login.component';
import { ProfilComponent } from './pages/profil.component/profil.component';
import { authGuard } from './shared/guards/auth-guard';
import { DolgozoKezelo } from './pages/dolgozo-kezelo/dolgozo-kezelo';
import { Rendelesek } from './pages/rendelesek/rendelesek';

export const routes: Routes = [
    { path: '', component: DolgozoKezelo, canActivate:[authGuard]},
    { path: 'login', component: LoginComponent},
    { path: 'rendelesek', component: Rendelesek},
    { path: 'profil', component: ProfilComponent},
    { path: '**', redirectTo: ''} // Átírányít a bejelentkező oldalra
];

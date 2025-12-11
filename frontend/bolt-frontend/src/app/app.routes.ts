import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login.component/login.component';
import { ProfilComponent } from './pages/profil.component/profil.component';
import { adminGuard, authGuard } from './shared/guards/auth-guard';
import { DolgozoKezelo } from './pages/dolgozo-kezelo/dolgozo-kezelo';
import { Rendelesek } from './pages/rendelesek/rendelesek';
import { Naplo } from './pages/naplo/naplo/naplo';
import { Tevekenysegek } from './pages/tevekenysegek/tevekenysegek/tevekenysegek';

export const routes: Routes = [
    { path: '', component: Rendelesek, canActivate:[authGuard]},
    { path: 'login', component: LoginComponent},
    { path: 'rendelesek', component: Rendelesek, canActivate:[authGuard]},
    //{ path: 'profil', component: ProfilComponent, canActivate:[authGuard]},
    { path: 'naplo', component: Naplo, canActivate:[adminGuard]},
    { path: 'tevekenysegek', component: Tevekenysegek, canActivate:[authGuard]},
    { path: 'dolgozok', component: DolgozoKezelo, canActivate:[adminGuard]},
    { path: '**', redirectTo: ''} // Átírányít a bejelentkező oldalra
];

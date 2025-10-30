import { Component, OnInit } from '@angular/core';
import { TevekenysegDTO } from '../../../shared/models/tevekenyseg-dto.model';
import { Dolgozo } from '../../../shared/models/dolgozo.model';
import { TevekenysegNaploDTO } from '../../../shared/models/tevekenyseg-naplo-dto.model';
import { TevekenysegService } from '../../../shared/services/tevekenysegService/tevekenyseg-service';
import { AuthService } from '../../../shared/services/auth.service';
import { AsyncPipe, DatePipe } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { FormsModule } from '@angular/forms';
import { MAT_DATE_LOCALE, provideNativeDateAdapter } from '@angular/material/core';

@Component({
  selector: 'app-tevekenysegek',
  imports: [MatTableModule, MatButtonModule, MatExpansionModule, MatFormFieldModule, MatInputModule, MatDatepickerModule, FormsModule],
  providers: [
    provideNativeDateAdapter(),
    { provide: MAT_DATE_LOCALE, useValue: 'hu-HU' },
    DatePipe
  ],
  templateUrl: './tevekenysegek.html',
  styleUrl: './tevekenysegek.scss'
})

//    AZ ID-T ÁT KELL ADNI BACKENDEN
export class Tevekenysegek implements OnInit {
  
  maiElvegzendo: TevekenysegDTO[] = [];
  maiElvegzettek: TevekenysegNaploDTO[] = [];
  osszesTevekenyseg: TevekenysegDTO[] = [];

  ujTevekenysegMegjelenitese = false;
  szerkesztett: TevekenysegDTO | null = null;
  isAdmin = false;

  ujTevekenyseg: Partial<TevekenysegDTO> = {
    megnevezes: '',
    leiras: '',
    kezdoDatum: ''
  };

  constructor(
    private tevekenysegService: TevekenysegService,
    private authService: AuthService,
    private datePipe: DatePipe
  ) {}



  ngOnInit(): void {
    this.loadMaiFeladatok();
    this.loadMaiElvegzettek();

    const user = this.authService.getUser();
    this.isAdmin = user?.szerepkor === 'ADMIN';
    if (this.isAdmin) {
      this.loadOsszesTevekenyseg();
    }
  }

  loadMaiFeladatok() {
    this.tevekenysegService.getMaiElvegzendoFeladatok().subscribe(data => {
      this.maiElvegzendo = data;
    });
  }

  loadMaiElvegzettek() {
    this.tevekenysegService.getMaiElvegzettFeladatok().subscribe(data => {
      this.maiElvegzettek = data;
    });
  }

  loadOsszesTevekenyseg() {
    this.tevekenysegService.getOsszesTevekenyseg().subscribe(data => {
      this.osszesTevekenyseg = data;
    });
  }
  elvegzes(tevekenyseg: TevekenysegDTO) {
    const user = this.authService.getUser();
    if (!user) {
      alert('Nincs bejelentkezett felhasználó!');
      return;
    }

    const today = this.datePipe.transform(new Date(), 'yyyy-MM-dd')!;
    this.tevekenysegService.feladatElvegzese(tevekenyseg.id, user.id, today).subscribe(() => {
      this.loadMaiFeladatok();
      this.loadMaiElvegzettek();
    });
  }

  torol(id: number) {
    if (confirm('Biztosan törlöd a tevékenységet?')) {
      this.tevekenysegService.torolTevekenyseg(id).subscribe(() => {
        this.loadOsszesTevekenyseg();
      });
    }
  }

  modositInit(tevekenyseg: TevekenysegDTO) {
    this.szerkesztett = { ...tevekenyseg };
  }

  mentesModositas() {
    if (!this.szerkesztett) return;
    this.tevekenysegService.modositTevekenyseg(this.szerkesztett.id, this.szerkesztett).subscribe(() => {
      this.szerkesztett = null;
      this.loadOsszesTevekenyseg();
    });
  }

  submitUjTevekenyseg() {
    if (!this.ujTevekenyseg.megnevezes) return;
    this.tevekenysegService.ujTevekenyseg(this.ujTevekenyseg as TevekenysegDTO).subscribe(() => {
      this.ujTevekenysegMegjelenitese = false;
      this.ujTevekenyseg = { megnevezes: '', leiras: '', kezdoDatum: '' };
      this.loadOsszesTevekenyseg();
    });
  }

}

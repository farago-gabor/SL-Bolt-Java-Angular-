import { Component, OnInit } from '@angular/core';
import { TevekenysegDTO } from '../../../shared/models/tevekenyseg-dto.model';
import { Dolgozo } from '../../../shared/models/dolgozo.model';
import { NaploDTO } from '../../../shared/models/naplo-dto.model';
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
import { MAT_DATE_LOCALE, MatOptionModule, provideNativeDateAdapter } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-tevekenysegek',
  imports: [
    MatTableModule, MatButtonModule, MatExpansionModule, MatFormFieldModule, MatInputModule, MatDatepickerModule, FormsModule, DatePipe, MatOptionModule,
    MatSelectModule
  ],
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
  maiElvegzettek: NaploDTO[] = [];
  osszesTevekenyseg: TevekenysegDTO[] = [];

  ujTevekenysegMegjelenitese = false;
  szerkesztett: TevekenysegDTO | null = null;
  isAdmin = false;

  ujTevekenyseg: Partial<TevekenysegDTO> = {
    megnevezes: '',
    leiras: '',
    kezdoDatum: '',
    gyakorisag: 'HETI',
    idopontok: []
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
    this.isAdmin = user?.szerepkor === 'admin';
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
  elvegzes(tevekenyseg: TevekenysegDTO, idopontId?: number) {
    const user = this.authService.getUser();
    if (!user) {
      alert('Nincs bejelentkezett felhasználó!');
      return;
    }

    const today = this.datePipe.transform(new Date(), 'yyyy-MM-dd')!;

    this.tevekenysegService.feladatElvegzese(
      tevekenyseg.id,
      user.id,
      today,
      idopontId
    ).subscribe({
      next: () => {
        this.loadMaiFeladatok();
        this.loadMaiElvegzettek();
      },
      error: (err) => console.error('Hiba az elvégzésnél:', err)
    });
  }


  torol(id: number) {
    if (confirm('Biztosan törlöd a tevékenységet?')) {
      console.log('Törlendő tevékenység ID:', id);
      this.tevekenysegService.torolTevekenyseg(id).subscribe(() => {
        this.loadOsszesTevekenyseg();
        this.loadMaiFeladatok();
      });
    }
  }

  modositInit(tevekenyseg: TevekenysegDTO) {
    this.szerkesztett = { 
      ...tevekenyseg,
      idopontok: tevekenyseg.idopontok ? [...tevekenyseg.idopontok] : []
    };
  }

  mentesModositas() {
    if (!this.szerkesztett) return;

    const dtoToSend: TevekenysegDTO = {
      ...this.szerkesztett,
      kezdoDatum: this.szerkesztett.kezdoDatum 
        ? this.datePipe.transform(this.szerkesztett.kezdoDatum, 'yyyy-MM-dd') || ''
        : this.szerkesztett.kezdoDatum 
    };

  this.tevekenysegService.modositTevekenyseg(this.szerkesztett.id!, dtoToSend)
    .subscribe(() => {
      this.szerkesztett = null;
      this.loadOsszesTevekenyseg();
      this.loadMaiFeladatok();
    });
  }


  // új időpont hozzáadása
  addIdopont() {
    this.ujTevekenyseg.idopontok!.push({ nap: '', idopont: '' });
  }

  // időpont törlése
  removeIdopont(index: number) {
    this.ujTevekenyseg.idopontok!.splice(index, 1);
  }

  // submit
  submitUjTevekenyseg() {
  if (!this.ujTevekenyseg.megnevezes) return;

  const dto: TevekenysegDTO = {
    ...this.ujTevekenyseg,
    kezdoDatum: this.ujTevekenyseg.kezdoDatum
      ? this.datePipe.transform(this.ujTevekenyseg.kezdoDatum, 'yyyy-MM-dd')!
      : ''
  } as TevekenysegDTO;

  console.log('Új tevékenység DTO:', dto);

  this.tevekenysegService.ujTevekenyseg(dto).subscribe(() => {
    this.ujTevekenysegMegjelenitese = false;
    this.ujTevekenyseg = { megnevezes: '', leiras: '', kezdoDatum: '' };
    this.loadOsszesTevekenyseg();
    this.loadMaiFeladatok();
  });
}

}

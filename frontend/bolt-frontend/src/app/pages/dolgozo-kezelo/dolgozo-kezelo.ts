import { Component, OnInit } from '@angular/core';
import { DolgozoService } from '../../shared/services/dolgozoService/dolgozo-service';
import { Dolgozo } from '../../shared/models/dolgozo.model';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-dolgozo-kezelo',
  imports: [MatTableModule, MatCardModule, FormsModule, MatFormFieldModule, MatExpansionModule, MatInputModule, MatButtonModule, MatOptionModule, MatSelectModule],
  templateUrl: './dolgozo-kezelo.html',
  styleUrl: './dolgozo-kezelo.scss'
})
export class DolgozoKezelo implements OnInit{

  dolgozok: DolgozoJelszoval[] = [];
  displayedColumns: string[] = ['email', 'nev', 'szerepkor', 'gombok']

  // Új dolgozó adatai
  ujDolgozo: { nev: string; email: string; ujJelszo: string } = {
    nev: '',
    email: '',
    ujJelszo: ''
  };

  ujDolgozoMegjelenitese = false;

  szerkesztettDolgozo: DolgozoJelszoval | null = null;
  szerepkorok: string[] = ['admin', 'dolgozo'];


  constructor(private dolgozoService: DolgozoService) {}

  ngOnInit(): void {
    this.dolgozokBetolt();
  }

  dolgozokBetolt() {
    this.dolgozoService.osszesDolgozo().subscribe({
      next: lekertDolgozok => this.dolgozok = lekertDolgozok.map(d => ({...d, ujJelszo: ''})),
      error: err => console.error("Hiba a dolgozók betöltésekor:", err)
    });
  }

  // Új dolgozó felvétele
  submitUjDolgozo() {
    const { nev, email, ujJelszo } = this.ujDolgozo;

    if (!nev || !email || !ujJelszo) {
      alert('Kérlek, töltsd ki az összes mezőt!');
      return;
    }

    this.dolgozoService.ujDolgozo(nev, email, ujJelszo).subscribe({
      next: dolgozo => {
        // Frissítjük a listát
        this.dolgozok.push({ ...dolgozo, ujJelszo: '' });
        // Űrlap törlése
        this.ujDolgozo = { nev: '', email: '', ujJelszo: '' };
        this.ujDolgozoMegjelenitese = false;
        this.dolgozokBetolt();
      },
      error: err => console.error('Hiba új dolgozó hozzáadásakor:', err)
    });
  }

    szerkesztDolgozo(dolgozo: DolgozoJelszoval) {
    this.szerkesztettDolgozo = { ...dolgozo }; // clone
  }

  mentesDolgozo() {
    if (!this.szerkesztettDolgozo) return;

    this.dolgozoService.modositDolgozo(this.szerkesztettDolgozo.id, this.szerkesztettDolgozo)
      .subscribe(() => {
        this.szerkesztettDolgozo = null;
        this.dolgozokBetolt();
      });
  }

  ujJelszo(dolgozo: DolgozoJelszoval) {
    if (!dolgozo.ujJelszo) return;

    this.dolgozoService.modositJelszo(dolgozo.id, dolgozo.ujJelszo)
      .subscribe(() => {
        dolgozo.ujJelszo = '';
      });
  }

  torolDolgozo(id: number) {
    if (confirm("Biztosan törölni akarod a dolgozót?")) {
      this.dolgozoService.torolDolgozo(id)
        .subscribe(() => this.dolgozokBetolt());
    }
  }

}

interface DolgozoJelszoval extends Dolgozo {
  ujJelszo?: string;
}

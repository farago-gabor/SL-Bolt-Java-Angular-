import { Component, OnInit } from '@angular/core';
import { RendelesTetelDTO } from '../../shared/models/rendeles-tetel-dto.model';
import { RendelesService } from '../../shared/services/rendelesService/rendeles-service';
import { RendelesDTO } from '../../shared/models/rendeles-dto.model';

import { MatTableModule } from '@angular/material/table';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatButtonModule } from '@angular/material/button';
import { MatFormField } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import {MAT_DATE_LOCALE, MatOptionModule, provideNativeDateAdapter} from '@angular/material/core';
import { AuthService } from '../../shared/services/auth.service';
import { AsyncPipe, DatePipe } from '@angular/common';
import { Arucikk } from '../../shared/models/arucikk.model';
import { MatAutocomplete, MatAutocompleteModule } from '@angular/material/autocomplete';
import { map, Observable, startWith } from 'rxjs';


@Component({
  selector: 'app-rendelesek',
  imports: [
    MatTableModule, MatExpansionModule, MatButtonModule, MatFormField, MatInputModule,
    MatIconModule, MatCheckboxModule, FormsModule, MatDatepickerModule, MatAutocomplete, ReactiveFormsModule,
    MatAutocompleteModule, AsyncPipe, MatOptionModule
],
  providers: [
    provideNativeDateAdapter(),
    {
      provide: MAT_DATE_LOCALE, useValue: 'hu-HU'
    },
    DatePipe
  ],
  templateUrl: './rendelesek.html',
  styleUrl: './rendelesek.scss'
})
export class Rendelesek implements OnInit {

  rendelesek: RendelesDTO[] = [];
  rendelesTetelek: { [rendelesId: number]: RendelesTetelDTO[] } = {};
  ujRendelesMegjelenitese = false;
  sikeresTorles = false;

  arucikkek: Arucikk[] = [];

  //autocomplete megvalósítása:
  filteredArucikkek: { [index: number]: Observable<string[]> } = {}; // Ez tartalmazza minden egyes tétel autocomplete szűrt listáját.
  arucikkNevControls: { [index: number]: FormControl } = {}; // Minden egyes input mezőhöz, ahol gépeled a nevet, kell egy külön FormControl, hogy reagálni tudjon a változásokra
  
  displayedColumns = ['tetelek', 'hatarido', 'vevo', 'statusz', 'dolgozoId', 'muveletek'];

  ujRendeles = {
    email: '',
    telefonszam: '',
    hatarido: '',
    dolgozoId: -1,
    tetelek: [] as RendelesTetelDTO[]
  };

  szerkesztettRendeles: RendelesDTO | null = null; // sátusz módosításához és ha módosítást megvalósítok

  

  constructor(private rendelesService: RendelesService, private authService: AuthService, private datePipe: DatePipe) {}

  ngOnInit(): void {
    this.loadRendelesek();
    this.loadArucikkek();

    const user = this.authService.getUser();
    if (user?.id != null) {
      this.ujRendeles.dolgozoId = user.id;
    } else {
      console.warn('Dolgozó ID nem elérhető! Nem lehet rendelést leadni.');
    }
  }

  loadRendelesek() {
    this.rendelesService.osszesRendeles().subscribe(data => {
      this.rendelesek = data;

      for (let r of data) {
        this.rendelesService.getRendelesTetelek(r.id).subscribe(tetelek => {
          this.rendelesTetelek[r.id] = tetelek;
        });
      }
    });
  }

  loadArucikkek() {
    this.rendelesService.osszesArucikk().subscribe( data => {
      this.arucikkek = data;
    })
  }

  submitUjRendeles() {
    const { email, telefonszam, hatarido, dolgozoId, tetelek } = this.ujRendeles;
    const formattedHatarido = hatarido ? this.datePipe.transform(hatarido, 'yyyy-MM-dd') ?? '' : '';
    this.rendelesService.ujRendeles(email, telefonszam, formattedHatarido, dolgozoId, tetelek).subscribe(() => {
      this.ujRendelesMegjelenitese = false;
      this.ujRendeles = { email: '', telefonszam: '', hatarido: '', dolgozoId, tetelek: [] };
      this.loadRendelesek();
    });
  }

  modositStatusz(rendeles: RendelesDTO) {
    
    this.szerkesztettRendeles = rendeles;

  }

  torolRendeles(id: number) {
    if (confirm('Biztosan törölni akarod?')) {
      this.rendelesService.torolRendeles(id).subscribe(() => {
        this.sikeresTorles = true;
        this.loadRendelesek();
      });
    }
  }


  mentesStatusz() {
    if (!this.szerkesztettRendeles) return;

    const r = this.szerkesztettRendeles;

    this.rendelesService.modositStatusz(
      r.id,
      r.beerkezet,
      r.felreteve,
      r.szoltam,
      r.elvitte
    ).subscribe(() => {
      this.szerkesztettRendeles = null;
    });
  }

  // autocompletehez:

  addTetel() {
    const index = this.ujRendeles.tetelek.length;

    this.ujRendeles.tetelek.push({
      arucikkNev: '',
      mennyiseg: 1,
      megjegyzes: ''
    });

    // Autocomplete control és szűrés beállítása
    this.arucikkNevControls[index] = new FormControl('');
    this.filteredArucikkek[index] = this.arucikkNevControls[index].valueChanges.pipe(
      startWith(''),
      map(value => this._filterArucikkek(value || ''))
    );
  }

  private _filterArucikkek(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.arucikkek
      .map(a => a.megnevezes)
      .filter(option => option.toLowerCase().includes(filterValue));
  }
    
}


import { Component, OnInit } from '@angular/core';
import { DolgozoService } from '../../shared/services/dolgozoService/dolgozo-service';
import { Dolgozo } from '../../shared/models/dolgozo.model';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-dolgozo-kezelo',
  imports: [MatTableModule, MatCardModule],
  templateUrl: './dolgozo-kezelo.html',
  styleUrl: './dolgozo-kezelo.scss'
})
export class DolgozoKezelo implements OnInit{

  dolgozok: DolgozoJelszoval[] = [];
  displayedColumns: string[] = ['email', 'nev', 'szerepkor', 'gombok']
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

}

interface DolgozoJelszoval extends Dolgozo {
  ujJelszo?: string;
}

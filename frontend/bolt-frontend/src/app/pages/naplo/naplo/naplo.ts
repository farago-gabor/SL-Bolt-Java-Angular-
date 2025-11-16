import { Component, OnInit } from '@angular/core';
import { NaploDTO } from '../../../shared/models/naplo-dto.model';
import { TevekenysegService } from '../../../shared/services/tevekenysegService/tevekenyseg-service';
import { TevekenysegDTO } from '../../../shared/models/tevekenyseg-dto.model';
import { MatTableModule } from '@angular/material/table';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-naplo',
  imports: [MatTableModule],
  templateUrl: './naplo.html',
  styleUrl: './naplo.scss'
})
export class Naplo implements OnInit {
  naplo: NaploDTO[] = [];

  displayedColumns = ['id', 'tevekenysegMegnevezes', 'tevekenysegLeiras', 'datum', 'dolgozoNev'];

  constructor(private tevekenysegService: TevekenysegService) {}

  ngOnInit(): void {
    this.tevekenysegService.getOsszesNaplobejegyzes().subscribe(data => {
      this.naplo = data.map(d => ({
        ...d,
        datum: new Date(d.datum).toLocaleDateString() // optional formatting
      }));
    });
  }

}

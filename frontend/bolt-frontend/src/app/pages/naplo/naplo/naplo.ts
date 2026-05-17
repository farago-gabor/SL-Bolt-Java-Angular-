import { Component, OnInit } from '@angular/core';
import { NaploDTO } from '../../../shared/models/naplo-dto.model';
import { TevekenysegService } from '../../../shared/services/tevekenysegService/tevekenyseg-service';
import { TevekenysegDTO } from '../../../shared/models/tevekenyseg-dto.model';
import { MatTableModule } from '@angular/material/table';
import { AsyncPipe } from '@angular/common';
import { MatPaginatorModule } from '@angular/material/paginator';

@Component({
  selector: 'app-naplo',
  imports: [MatTableModule, MatPaginatorModule],
  templateUrl: './naplo.html',
  styleUrl: './naplo.scss'
})
export class Naplo implements OnInit {
  naplo: NaploDTO[] = [];

  displayedColumns = ['id', 'tevekenysegMegnevezes', 'tevekenysegLeiras', 'datum', 'idopont', 'dolgozoNev'];

  page = 0;
  size = 10;
  totalElements = 0;

  constructor(private tevekenysegService: TevekenysegService) {}

  ngOnInit(): void {
    this.tevekenysegService.getOsszesNaplobejegyzes().subscribe(data => {
      this.naplo = data.map(d => ({
        ...d,
        datum: new Date(d.datum).toLocaleDateString() // optional formatting
      }));
    });
  }

  loadData() {
    this.tevekenysegService
      .getNaplobejegyzesLapozva(this.page, this.size)
      .subscribe(response => {

        this.naplo = response.content.map((d: NaploDTO) => ({
          ...d,
          datum: new Date(d.datum).toLocaleDateString()
        }));

        this.totalElements = response.totalElements;
      });
  }

}

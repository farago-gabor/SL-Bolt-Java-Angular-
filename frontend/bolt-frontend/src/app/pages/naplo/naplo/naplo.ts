import { Component, OnInit } from '@angular/core';
import { TevekenysegNaploDTO } from '../../../shared/models/tevekenyseg-naplo-dto.model';
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
  naplo: TevekenysegNaploDTO[] = [];

  displayedColumns = ['megnevezes', 'datum', 'dolgozo', 'elvegzett'];

  constructor(private tevekenysegService: TevekenysegService) {}

  ngOnInit(): void {
    this.tevekenysegService.getOsszesNaplobejegyzes().subscribe(data => {
      this.naplo = data;
    });
  }

}

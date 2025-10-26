import { Injectable } from '@angular/core';
import { environment } from '../../environment/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TevekenysegService {
  
    private url = `${environment.apiUrl}/rendelesek`;
  
    constructor(private http: HttpClient) {}

    getMaiElvegzendoFeladatok(){}

    feladatElvegzese(){}

    getMaiElvegzettFeladatok(){}

    getOsszesNaplobejegyzes(){}

    ujTevekenyseg(){}

    getOsszesTevekenyseg(){}

    modositTevekenyseg(){}

    torolTevekenyseg(){}

}

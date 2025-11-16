import { Injectable } from '@angular/core';
import { environment } from '../../environment/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TevekenysegDTO } from '../../models/tevekenyseg-dto.model';
import { NaploDTO } from '../../models/naplo-dto.model';

@Injectable({
  providedIn: 'root'
})
export class TevekenysegService {
  
    private url = `${environment.apiUrl}/tevekenysegek`;
  
    constructor(private http: HttpClient) {}

    getMaiElvegzendoFeladatok(): Observable<TevekenysegDTO[]> {
      return this.http.get<TevekenysegDTO[]>(`${this.url}/ma/elvegzendo`);
    }

      feladatElvegzese(
          tevekenysegId: number,
          dolgozoId: number,
          datum: string
      ): Observable<void> {
          const params = new HttpParams()
            .set('tevekenysegId', tevekenysegId)
            .set('dolgozoId', dolgozoId)
            .set('datum', datum);
          return this.http.post<void>(`${this.url}/elvegzes`, null, { params });
      }

    getMaiElvegzettFeladatok(): Observable<NaploDTO[]> {
      return this.http.get<NaploDTO[]>(`${this.url}/ma/elvegzettek`);
    }

    getOsszesNaplobejegyzes(): Observable<NaploDTO[]> {
      return this.http.get<NaploDTO[]>(`${this.url}/naplo`);
    }

    ujTevekenyseg(dto: TevekenysegDTO): Observable<void> {
      return this.http.post<void>(this.url, dto);
    }

    getOsszesTevekenyseg(): Observable<TevekenysegDTO[]> {
      return this.http.get<TevekenysegDTO[]>(this.url);
    }

    modositTevekenyseg(id: number, dto: TevekenysegDTO): Observable<void> {
      return this.http.put<void>(`${this.url}/${id}`, dto);
    }

    torolTevekenyseg(id: number): Observable<void> {
      return this.http.delete<void>(`${this.url}/${id}`);
    }

}

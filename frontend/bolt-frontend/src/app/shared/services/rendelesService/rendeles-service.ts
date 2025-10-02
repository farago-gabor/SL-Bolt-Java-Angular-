import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment';
import { RendelesDTO } from '../../models/rendeles-dto.model';
import { RendelesTetelDTO } from '../../models/rendeles-tetel-dto.model';

@Injectable({
  providedIn: 'root'
})
export class RendelesService {

  private url = `${environment.apiUrl}/rendelesek`;

  constructor(private http: HttpClient) {}

  ujRendeles(
    email: string,
    telefonszam: string,
    hatarido: string,
    dolgozoId: number,
    tetelek: RendelesTetelDTO[]
  ): Observable<RendelesDTO> {
    const params = new HttpParams()
      .set('email', email)
      .set('telefonszam', telefonszam)
      .set('hatarido', hatarido)
      .set('dolgozoId', dolgozoId.toString());

    return this.http.post<RendelesDTO>(this.url, tetelek, { params });
  }

  modositStatusz(
    id: number,
    beerkezet: boolean,
    felreteve: boolean,
    szoltam: boolean,
    elvitte: boolean
  ): Observable<RendelesDTO> {
    const params = new HttpParams()
      .set('beerkezet', beerkezet)
      .set('felreteve', felreteve)
      .set('szoltam', szoltam)
      .set('elvitte', elvitte);

    return this.http.put<RendelesDTO>(`${this.url}/${id}/statusz`, null, { params });
  }

  torolRendeles(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

  osszesRendeles(): Observable<RendelesDTO[]> {
    return this.http.get<RendelesDTO[]>(this.url);
  }

  getRendelesById(id: number): Observable<RendelesDTO> {
    return this.http.get<RendelesDTO>(`${this.url}/${id}`);
  }

  getRendelesTetelek(id: number): Observable<RendelesTetelDTO[]> {
    return this.http.get<RendelesTetelDTO[]>(`${this.url}/${id}/tetelek`);
  }
}

import { Injectable } from '@angular/core';
import { environment } from '../../environment/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Dolgozo } from '../../models/dolgozo.model';

@Injectable({
  providedIn: 'root'
})
export class DolgozoService {
  private url = `${environment.apiUrl}/dolgozok`;
  
  constructor(private http: HttpClient){}

    // ujDolgozo, modositDolgozo, modositJelszo, torolDolgozo, osszesDolgozo, getDolgozoById

  ujDolgozo(nev: string, email: string, jelszo: string): Observable<Dolgozo> {

    const params = new HttpParams()
        .set('nev', nev)
        .set('email', email)
        .set('jelszo', jelszo);

    return this.http.post<Dolgozo>(this.url, null, { params });

  }

  modositDolgozo(id: number, dolgozo: Dolgozo): Observable<Dolgozo> {
    return this.http.put<Dolgozo>(`${this.url}/${id}`, dolgozo);
  }

  modositJelszo(id: number, ujJelszo: string): Observable<Dolgozo> {
    const params = new HttpParams().set('ujJelszo', ujJelszo)
    return this.http.put<Dolgozo>(`${this.url}/${id}/jelszo`, null, { params }); 
  }

  torolDolgozo(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

  osszesDolgozo(): Observable<Dolgozo[]> {
    return this.http.get<Dolgozo[]>(this.url);
  }
  getDolgozoById(id: number): Observable<Dolgozo> {
    return this.http.get<Dolgozo>(`${this.url}/${id}`);
  }



}

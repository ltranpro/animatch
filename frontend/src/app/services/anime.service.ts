// frontend/src/app/services/anime.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AnimeService {
  // On ajoute bien le /top ici
  private apiUrl = 'http://localhost:8080/api/animes/top';

  constructor(private http: HttpClient) { }

  getAnimes(page: number, size: number): Observable<any> {
    // Les paramètres de pagination s'ajoutent à la fin : /top?page=0&size=10
    return this.http.get(`${this.apiUrl}?page=${page}&size=${size}`);
  }
}

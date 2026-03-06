import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
@Injectable({ providedIn: 'root' })
export class AnimeService {
  // 1. La base commune à TOUTES les requêtes
  private apiUrl = 'http://localhost:8080/api/animes';

  constructor(private http: HttpClient) { }

  // 2. Route pour le TOP (Home page)
  getTopAnimes(page: number, size: number): Observable<any> {
    // L'URL finale doit être exactement : http://localhost:8080/api/animes/top
    return this.http.get<any>(`${this.apiUrl}/top`, {
      params: new HttpParams().set('page', page).set('size', size)
    });
  }

  // 3. Route pour le CATALOGUE (Page A-Z)
  getAnimes(page: number, size: number, letter?: string): Observable<any> {
    let params = new HttpParams().set('page', page).set('size', size);
    if (letter) { params = params.set('letter', letter); }

    // Résultat : http://localhost:8080/api/animes/catalogue
    return this.http.get<any>(`${this.apiUrl}/catalogue`, { params });
  }
}

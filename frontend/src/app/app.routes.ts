import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { Top100Component } from './components/top-100/top-100.component';
import { AnimeListComponent } from './components/anime-list/anime-list.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },             // Page d'accueil par défaut
  { path: 'top-100', component: Top100Component },    // Ton travail actuel
  { path: 'animes', component: AnimeListComponent },  // Ta future page A-Z
  { path: '**', redirectTo: '' }                      // Si l'utilisateur tape n'importe quoi, retour accueil
];

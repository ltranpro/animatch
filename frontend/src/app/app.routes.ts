import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { Top100Component } from './components/top-100/top-100.component';
import { AllAnimesComponent } from './components/all-animes/all-animes.component'; // <-- On importe la PAGE
import { AnimeDetailComponent } from './components/anime-detail/anime-detail.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'top-100', component: Top100Component },
  { path: 'catalogue', component: AllAnimesComponent },

  // 1. On place l'ID avant le piège global
  { path: 'anime/:id', component: AnimeDetailComponent },

  // 2. Le piège global (Wildcard) TOUJOURS en dernier
  { path: '**', redirectTo: '' }
];

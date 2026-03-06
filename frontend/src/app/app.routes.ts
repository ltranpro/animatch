import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { Top100Component } from './components/top-100/top-100.component';
import { AllAnimesComponent } from './components/all-animes/all-animes.component'; // <-- On importe la PAGE

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'top-100', component: Top100Component },
  { path: 'catalogue', component: AllAnimesComponent }, // <-- On utilise la PAGE ici, pas la liste
  { path: '**', redirectTo: '' }
];

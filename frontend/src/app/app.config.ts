import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes'; // Importe le fichier de routes qu'on a créé
import { provideHttpClient } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes), // C'est cette ligne qui manquait !
    provideHttpClient()    // Assure-toi qu'il est là aussi pour ton AnimeService
  ]
};

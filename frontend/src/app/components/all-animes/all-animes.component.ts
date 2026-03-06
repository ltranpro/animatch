import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AnimeListComponent } from '../anime-list/anime-list.component';
import { AnimeService } from '../../services/anime.service';

@Component({
  selector: 'app-all-animes',
  standalone: true,
  imports: [CommonModule, AnimeListComponent], // On autorise l'utilisation de la grille ici
  templateUrl: './all-animes.component.html',
  styleUrl: './all-animes.component.scss'
})
export class AllAnimesComponent implements OnInit {
  animes: any[] = []; // Notre panier vide qui va recevoir les animés

  constructor(private animeService: AnimeService) {}

  ngOnInit(): void {
  // On appelle getAnimes avec page 0 et size 20
    this.animeService.getAnimes(0, 20).subscribe(data => {
    // Attention : comme ton API renvoie une page (avec content, totalElements, etc.)
    // il faut bien récupérer 'data.content' qui contient le tableau d'animés.
    this.animes = data.content;
  });
}
}

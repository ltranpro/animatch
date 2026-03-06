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
  animes: any[] = [];
  alphabet: string[] = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split('');
  selectedLetter: string = 'A';
  currentPage: number = 0;
  pageSize: number = 25;
  totalPages: number = 0;


  constructor(private animeService: AnimeService) {}

  ngOnInit(): void {
    this.loadAnimes();
  }

  loadAnimes(): void {
    // On appelle ton nouveau service avec la lettre sélectionnée
    this.animeService.getAnimes(this.currentPage, this.pageSize, this.selectedLetter)
      .subscribe(data => {
        this.animes = data.content;
        this.totalPages = data.totalPages;
      });
  }

  selectLetter(letter: string): void {
    this.selectedLetter = letter;
    this.currentPage = 0; // On reset la page à 0 quand on change de lettre
    this.loadAnimes();
  }
}

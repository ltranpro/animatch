import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; // Important pour le *ngIf
import { ActivatedRoute } from '@angular/router';
import { AnimeService } from '../../services/anime.service'; // Ajuste le chemin
import { Anime } from '../../models/anime.model';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-anime-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './anime-detail.component.html',
  styleUrl: './anime-detail.component.scss'
})
export class AnimeDetailComponent implements OnInit {
  anime: Anime | undefined;

  constructor(
    private route: ActivatedRoute,
    private animeService: AnimeService,
    private sanitizer: DomSanitizer // Ajoute ceci
  ) {}

  ngOnInit(): void {
    // Récupère l'ID depuis l'URL (ex: /anime/12)
    const id = Number(this.route.snapshot.paramMap.get('id'));

    if (id) {
      this.animeService.getAnimeById(id).subscribe({
        next: (data) => {
          this.anime = data;
        },
        error: (err) => {
          console.error('Erreur lors de la récupération de l anime', err);
        }
      });
    }
  }

  getSafeTrailerUrl(videoId: string): SafeResourceUrl {
  return this.sanitizer.bypassSecurityTrustResourceUrl(`https://www.youtube.com/embed/${videoId}`);
}
}

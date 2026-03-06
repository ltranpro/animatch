import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AnimeService } from '../../services/anime.service';

@Component({
  selector: 'app-top-100',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './top-100.component.html',
  styleUrl: './top-100.component.scss'
})
export class Top100Component implements OnInit {
  animes: any[] = [];
  isLoading = true;
  currentPage = 0;
  totalPages = 0;
  pageSize = 10;

  constructor(private animeService: AnimeService) {}

  ngOnInit() {
    this.loadAnimes();
  }

  loadAnimes() {
    this.isLoading = true;
    this.animeService.getAnimes(this.currentPage, this.pageSize).subscribe({
      next: (data: any) => {
        this.animes = data.content;
        this.totalPages = data.totalPages;
        this.isLoading = false;
      },
      error: (err) => {
        console.error("Erreur Backend :", err);
        this.isLoading = false;
      }
    });
  }

  nextPage() {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.loadAnimes();
    }
  }

  previousPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.loadAnimes();
    }
  }

  goToPage(event: any) {
    const pageSaisie = parseInt(event.target.value);
    if (!isNaN(pageSaisie) && pageSaisie >= 1 && pageSaisie <= this.totalPages) {
      this.currentPage = pageSaisie - 1;
      this.loadAnimes();
    } else {
      event.target.value = this.currentPage + 1;
    }
  }
}

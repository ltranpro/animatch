import { Component, Input } from '@angular/core'; // N'oublie pas d'importer Input
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-anime-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './anime-list.component.html',
  styleUrl: './anime-list.component.scss'
})
export class AnimeListComponent {
  // Cette ligne est la clé ! Elle crée la propriété que tu essaies de lier
  @Input() animes: any[] = [];
}

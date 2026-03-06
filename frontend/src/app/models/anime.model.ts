export interface Anime {
    id: number;
    title: string;
    synopsis: string;
    imageUrl: string;
    largeImageUrl: string;
    score: number;
    rank: number;
    popularity: number;
    type: string;
    source: string;
    episodes: number;
    status: string;
    duration: string;
    rating: string;
    year: number;
    trailerUrl: string; // L'ID YouTube
    genres: string;
    studios: string;
}

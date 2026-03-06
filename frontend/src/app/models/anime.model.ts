export interface Anime {
  id?: number;          // Le '?' signifie que l'ID est optionnel (car la BDD le génère)
  title: string;       // On force le titre à être une chaîne de caractères
  synopsis: string;    // Idem pour le résumé
  imageUrl: string;    // L'URL de l'image pour la balise <img>
}

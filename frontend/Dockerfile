# 1. Utiliser une image Node.js pour compiler et faire tourner Angular
FROM node:20-alpine

# 2. Définir le dossier de travail dans le conteneur
WORKDIR /app

# 3. Copier les fichiers de dépendances
COPY package*.json ./

# 4. Installer les dépendances
RUN npm install

# 5. Copier tout le reste du code source
COPY . .

# 6. Installer Angular CLI globalement dans le conteneur
RUN npm install -g @angular/cli

# 7. Exposer le port 4200 (port par défaut d'Angular)
EXPOSE 4200

# 8. Lancer l'application en mode développement
# Le flag --host 0.0.0.0 est crucial pour Docker
# On utilise npm start pour utiliser la config du package.json (avec le --poll)
CMD ["npm", "start", "--", "--host", "0.0.0.0", "--disable-host-check"]

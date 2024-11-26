# spark-file-analyzer
Tout le monde pense maîtriser les logs... jusqu'à ce débug nocturne en prod à 3h du matin, 6 mois après avoir touché au code pour la dernière fois. 🌙

5 tips essentiels pour des logs qui vous sauveront la vie :

1️⃣ Dès que possible, Adoptez le logging structuré : timestamp, error_type, user_id... Fini le parsing de texte à 3h du mat' !

2️⃣ Utilisez les niveaux de log intelligemment 
 🟩 INFO : "Commande validée" 
 🟨 WARN : "Paiement ralenti"
 🟥 ERROR : "Connexion DB perdue" 
 ❌ FATAL : "Plus de mémoire, arrêt système"

3️⃣ Contextualisez TOUJOURS vos logs 
WHO : ID utilisateur 
WHAT : Action réalisée 
WHERE : Service concerné 
WHY : Description détaillée

4️⃣ Une ligne de log = Une histoire complète Capturez tout le processus en une seule entrée : action, résultat, durée. Votre "vous du futur" vous remerciera.

5️⃣ Bannissez les données sensibles Pas de mot de passe, carte bancaire ou clé d'API dans les logs. Jamais. Jamais ... Jamais

## Exemple de workflow dans un projet plus large :
1. **Créer une nouvelle branche pour chaque tâche** :
   ```bash
   git checkout -b feature/nouvelle-fonctionnalite
   ```
2. **Committer les changements régulièrement** :

   ```bash
   git add .
   git commit -m "Ajout de la fonctionnalité X"
   ```
3. **Mettre à jour ta branche main avant de fusionner** :

   ```bash
   git checkout main
   git pull origin main
   ```

4. **Fusionner ta branche dans main** :

   ```bash
   git checkout feature/nouvelle-fonctionnalite
   git merge main
   ```

5. **Résoudre les conflits (si nécessaire) et pousser sur GitHub** :

   ```bash
    git push origin main
   ```

6. **Supprimer les branches locales et distantes après fusion** :

   ```bash
    git branch -d feature/nouvelle-fonctionnalite
    git push origin --delete feature/nouvelle-fonctionnalite
   ```

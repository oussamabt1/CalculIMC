# TP Android Studio – Calcul de l'IMC (Kotlin)

## Ouvrir le projet
1. Android Studio : **File > Open** puis choisir le dossier `CalculIMC`.
2. Attendre la synchronisation Gradle (connexion Internet requise la première fois).
3. Lancer sur l'émulateur (bouton Run).

## Structure
- `app/src/main/java/com/example/calculimc/MainActivity.kt` : logique (validation, calcul, catégories, Effacer)
- `app/src/main/res/layout/activity_main.xml` : interface
- `app/src/main/res/values/strings.xml` : textes visibles
- `app/src/main/res/values/colors.xml` : couleurs des catégories

## Tests à effectuer
| Données | Résultat attendu | Message |
|---|---|---|
| 70 kg, 1,75 m | 22,86 | Corpulence normale |
| 50 kg, 1,75 m | 16,33 | Insuffisance pondérale |
| 85 kg, 1,70 m | 29,41 | Surpoids |
| 100 kg, 1,70 m | 34,60 | Obésité modérée |
| Champ vide | aucun calcul | Message d'erreur |
| Taille = 0 | aucun calcul | Message d'erreur |

## À remettre
- Lien du dépôt GitHub (`git init`, `git add .`, `git commit`, puis push)
- Capture d'un calcul valide + capture d'une erreur de saisie
- `activity_main.xml`, `MainActivity.kt`, `strings.xml`
- Compte rendu des difficultés rencontrées

> L'IMC sert ici de support de programmation et ne remplace pas un avis médical.

package com.sirine.jeudetexte;

import java.util.Scanner;
import com.sirine.jeudetexte.Model.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans le jeu de texte RPG multijoueur !");

        Personnage joueur1 = null;
        Personnage joueur2 = null;

        try {
            // Player 1 character creation
            System.out.println("Joueur 1 - Choisissez votre personnage :");
            System.out.println("1. Mage");
            System.out.println("2. Guerrier");
            System.out.println("3. Voleur");
            System.out.print(">>> ");
            int choixPersonnage1 = scanner.nextInt();
            scanner.nextLine(); // Consomme la fin de ligne
            System.out.print("Entrez le nom de votre personnage (Joueur 1) : ");
            String nomPersonnage1 = scanner.nextLine();
            joueur1 = creerPersonnage(choixPersonnage1, nomPersonnage1);

            // Player 2 character creation
            System.out.println("\nJoueur 2 - Choisissez votre personnage :");
            System.out.println("1. Mage");
            System.out.println("2. Guerrier");
            System.out.println("3. Voleur");
            System.out.print(">>> ");
            int choixPersonnage2 = scanner.nextInt();
            scanner.nextLine(); // Consomme la fin de ligne
            System.out.print("Entrez le nom de votre personnage (Joueur 2) : ");
            String nomPersonnage2 = scanner.nextLine();
            joueur2 = creerPersonnage(choixPersonnage2, nomPersonnage2);

            // Main game loop
            while (joueur1.estvivant() && joueur2.estvivant()) {
                System.out.println("---------------------------------------------------------------------");

                afficherEtatPersonnages(joueur1, joueur2);

                // Player 1's turn
                System.out.println("\nTour de " + joueur1.getnom() + " (Joueur 1)");
                executerTour(joueur1, joueur2, scanner);

                if (!joueur2.estvivant())
                    break;

                // Player 2's turn
                System.out.println("---------------------------------------------------------------------");

                afficherEtatPersonnages(joueur1, joueur2);
                System.out.println("\nTour de " + joueur2.getnom() + " (Joueur 2)");
                executerTour(joueur2, joueur1, scanner);
            }

            determinerVainqueur(joueur1, joueur2);
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static Personnage creerPersonnage(int choix, String nom) {
        switch (choix) {
            case 1:
                return new Mage(nom);
            case 2:
                return new Guerrier(nom);
            case 3:
                return new Voleur(nom);
            default:
                System.out.println("Choix invalide. Sélection par défaut : Mage");
                return new Mage(nom);
        }
    }

    private static void executerTour(Personnage attaquant, Personnage cible, Scanner scanner) {
        int choixAction = -1;

        // Input validation loop
        while (true) {
            try {
                System.out.println("---------------------------------------------------------------------");
                System.out.println("Choisissez votre action :");
                System.out.println("1. Attaquer");
                System.out.println("2. Utiliser une compétence spéciale");
                System.out.println("3. Défendre");
                System.out.println("4. Se soigner");
                System.out.print(">>> ");

                // Validate input
                if (scanner.hasNextInt()) {
                    choixAction = scanner.nextInt();
                    scanner.nextLine(); // Clear the buffer
                    if (choixAction >= 1 && choixAction <= 4) {
                        break; // Valid input
                    } else {
                        System.out.println("Veuillez entrer un nombre entre 1 et 4.");
                    }
                } else {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
                    scanner.nextLine(); // Clear invalid input
                }
            } catch (Exception e) {
                System.out.println("Une erreur est survenue lors de la saisie. Veuillez réessayer.");
                scanner.nextLine(); // Clear the buffer in case of unexpected errors
            }
        }

        // Perform action
        switch (choixAction) {
            case 1:
                // segmi fonctionalite attaquer
                // attaquant.attaquer(cible);
                System.out.println("Attaque effectuée");
                break;
            case 2:
            // segmi fonctionalite utilisercompetence
                // attaquant.utilisercompetence(cible);
                System.out.println("Compétence spéciale utilisée");
                break;
            case 3:
                // segmi fonctionalite defance
                // attaquant.defance(cible);
                System.out.println("Défense effectuée");
                break;
            case 4:
                // segmi fonctionalite soin
                // attaquant.soin();
                System.out.println("Soins effectués");
                break;
        }
    }

    private static void afficherEtatPersonnages(Personnage joueur1, Personnage joueur2) {
        System.out.println("\n--- État des personnages ---");
        System.out.println(joueur1.getnom() + " (Joueur 1) : Points de vie = " + joueur1.getPointdevie() +
                ", Cœurs = " + joueur1.getCoeur());
        System.out.println(joueur2.getnom() + " (Joueur 2) : Points de vie = " + joueur2.getPointdevie() +
                ", Cœurs = " + joueur2.getCoeur());
    }

    private static void determinerVainqueur(Personnage joueur1, Personnage joueur2) {
        if (joueur1.estvivant()) {
            System.out.println("Félicitations ! " + joueur1.getnom() + " (Joueur 1) a vaincu " + joueur2.getnom()
                    + " (Joueur 2) !");
        } else {
            System.out.println("Félicitations ! " + joueur2.getnom() + " (Joueur 2) a vaincu " + joueur1.getnom()
                    + " (Joueur 1) !");
        }
    }
}

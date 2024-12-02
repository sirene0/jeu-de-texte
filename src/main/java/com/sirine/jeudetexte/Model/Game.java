package com.sirine.jeudetexte.Model;

import java.util.Scanner;

public class Game {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Bienvenue dans le jeu d'aventure!");
        
        // Création du personnage du joueur
        Personnage joueur = choixPersonnage();
        
        // Création d'un adversaire
        Personnage adversaire = creerAdversaire();
        
        // Boucle principale du jeu
    
    }

    private static Personnage choixPersonnage() {
        while (true) {
            System.out.println("\nChoisissez votre personnage :");
            System.out.println("1. Mage");
            System.out.println("2. Guerrier");
            System.out.println("3. Voleur");
            System.out.println("Votre choix : ");

            try {
                int choix = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                System.out.println("Entrez le nom de votre personnage : ");
                String nom = scanner.nextLine();

                switch (choix) {
                    case 1: return new Mage(nom);
                    case 2: return new Guerrier(nom);
                    case 3: return new Voleur(nom);
                    default: 
                        System.out.println("Choix invalide. Réessayez.");
                }
            } catch (Exception e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private static Personnage creerAdversaire() {
        // Création d'un adversaire aléatoire
        String[] nomsAdversaires = {"Gobelin", "Orc", "Dragon", "Bandit", "Sorcier"};
        String nomAdversaire = nomsAdversaires[(int)(Math.random() * nomsAdversaires.length)];
        
        // Création d'un adversaire avec un type aléatoire
        int typeAdversaire = (int)(Math.random() * 3);
        switch (typeAdversaire) {
            case 0: return new Mage(nomAdversaire);
            case 1: return new Guerrier(nomAdversaire);
            default: return new Voleur(nomAdversaire);
        }
    }

    private static void tourJoueur(Personnage joueur, Personnage adversaire) {
        System.out.println("\n--- Tour du Joueur ---");
        System.out.println("Que voulez-vous faire ?");
        System.out.println("1. Attaquer");
        System.out.println("2. Utiliser une compétence spéciale");
        System.out.println("3. Se défendre");
        System.out.println("4. Se soigner");
        
        try {
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choix) {
                case 1: 
                    joueur.attaquer(adversaire);
                    break;
                case 2: 
                    joueur.utilisercompetence(adversaire);
                    break;
                case 3: 
                    joueur.defance(adversaire);
                    break;
                case 4: 
                    joueur.soin();
                    break;
                default: 
                    System.out.println("Choix invalide. Passage au tour suivant.");
            }
        } catch (Exception e) {
            System.out.println("Entrée invalide. Passage au tour suivant.");
            scanner.nextLine(); // Clear the invalid input
            System.out.println("------------");
        }
    }

    private static void tourAdversaire(Personnage adversaire, Personnage joueur) {
        System.out.println("\n--- Tour de l'Adversaire ---");
        
        // Choix aléatoire de l'action de l'adversaire
        try {
            int choixAdversaire = (int)(Math.random() * 4);

        switch (choixAdversaire) {
            case 0: 
                adversaire.attaquer(joueur);
                break;
            case 1: 
                adversaire.utilisercompetence(joueur);
                break;
            case 2: 
                adversaire.defance(joueur);
                break;
            case 3: 
                adversaire.soin();
                break;
            default:
            System.out.println("Choix invalide. Passage au tour suivant.");
        }
        } catch (Exception e) {
            System.out.println("Entrée invalide. Passage au tour suivant.");
            scanner.nextLine(); // Clear the invalid input
            System.out.println("------------");
        }
        
    }
}
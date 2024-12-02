package com.sirine.jeudetexte;

import com.sirine.jeudetexte.Model.Personnage;
import com.sirine.jeudetexte.Model.Mage;
import com.sirine.jeudetexte.Model.Guerrier;
import com.sirine.jeudetexte.Model.Voleur;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Character selection
        System.out.println("Bienvenue dans le jeu de texte RPG !");
        System.out.println("Choisissez votre personnage :");
        System.out.println("1. Mage");
        System.out.println("2. Guerrier");
        System.out.println("3. Voleur");
        
        Personnage joueur = null;
        Personnage ennemi = null;
        
        try {
            int choixPersonnage = scanner.nextInt();
            System.out.print("Entrez le nom de votre personnage : ");
            String nomPersonnage = scanner.next();
            
            // Create player character based on selection
            switch (choixPersonnage) {
                case 1:
                    joueur = new Mage(nomPersonnage);
                    break;
                case 2:
                    joueur = new Guerrier(nomPersonnage);
                    break;
                case 3:
                    joueur = new Voleur(nomPersonnage);
                    break;
                default:
                    System.out.println("Choix invalide. Sélection par défaut : Mage");
                    joueur = new Mage(nomPersonnage);
            }
            
            // Create enemy character
            System.out.println("Un ennemi apparaît !");
            ennemi = genererEnnemi();
            
            // Main game loop
            while (joueur.estvivant() && ennemi.estvivant()) {
                afficherEtatPersonnages(joueur, ennemi);
                
                // Player's turn
                System.out.println("\nTour de " + joueur.getnom());
                System.out.println("Choisissez votre action :");
                System.out.println("1. Attaquer");
                System.out.println("2. Utiliser une compétence spéciale");
                System.out.println("3. Défendre");
                System.out.println("4. Se soigner");
                
                int choixAction = scanner.nextInt();
                
                switch (choixAction) {
                    case 1:
                        joueur.attaquer(ennemi);
                        break;
                    case 2:
                        joueur.utilisercompetence(ennemi);
                        break;
                    case 3:
                        joueur.defance(ennemi);
                        break;
                    case 4:
                        joueur.soin();
                        break;
                    default:
                        System.out.println("Action invalide. Attaque par défaut.");
                        joueur.attaquer(ennemi);
                }
                
                // Enemy's turn if still alive
                if (ennemi.estvivant()) {
                    System.out.println("\nTour de l'ennemi : " + ennemi.getnom());
                    ennemi.attaquer(joueur);
                }
            }
            
            // Game result
            if (joueur.estvivant()) {
                System.out.println("Félicitations ! Vous avez vaincu " + ennemi.getnom() + " !");
            } else {
                System.out.println("Game Over ! Vous avez été vaincu par " + ennemi.getnom() + ".");
            }
            
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
    
    // Method to generate a random enemy
    private static Personnage genererEnnemi() {
        int choixEnnemi = (int) (Math.random() * 3) + 1;
        String[] nomsEnemis = {"Gobelin", "Orc", "Sorcier Noir"};
        String nomEnnemi = nomsEnemis[(int) (Math.random() * nomsEnemis.length)];
        
        switch (choixEnnemi) {
            case 1:
                return new Mage(nomEnnemi);
            case 2:
                return new Guerrier(nomEnnemi);
            case 3:
                return new Voleur(nomEnnemi);
            default:
                return new Mage(nomEnnemi);
        }
    }
    
    // Method to display character status
    private static void afficherEtatPersonnages(Personnage joueur, Personnage ennemi) {
        System.out.println("\n--- État des personnages ---");
        System.out.println(joueur.getnom() + " : Points de vie = " + joueur.getPointdevie() + ", Cœurs = " + joueur.getCoeur());
        System.out.println(ennemi.getnom() + " : Points de vie = " + ennemi.getPointdevie() + ", Cœurs = " + ennemi.getCoeur());
    }
}
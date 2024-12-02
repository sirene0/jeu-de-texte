package com.sirine.jeudetexte.Model;

import java.util.Scanner;

public class Voleur extends Personnage {
    private int energie;

    public Voleur(String nom, int pointsDeVieMax, int pointdevie, int degat, int energie) {
        super(nom, pointsDeVieMax, pointdevie, degat);
        this.energie = energie;
    }

    public Voleur(String nom) {
        // Valeurs par défaut pour un mage
        super(nom, 80, 80, 20);
        this.energie = 5;
    }

    public void attaquer(Personnage cible) {
        // Menu pour choisir une attaque
        System.out.println("\n--- Choisissez une attaque ---");
        System.out.println("1. Coup de base (20 dégâts)");
        System.out.println("2. double lame avec deux armes(40 dégâts, consomme de l'énergie)");
        System.out.println("3. pluie de dagues (40 dégâts, mais réduit vos points de vie de 5)");
        System.out.print("Choisissez une compétence : ");
        int choix = -1;
        try (Scanner scanner = new Scanner(System.in)) {
            while (choix < 1 || choix > 3) {
                if (scanner.hasNextInt()) {
                    choix = scanner.nextInt();
                    if (choix < 1 || choix > 3) {
                        System.out.println("Choix invalide, veuillez entrer un nombre entre 1 et 3.");
                    }
                } else {
                    System.out.println("Erreur de saisie. Veuillez entrer un nombre valide.");
                    scanner.next(); // consomme l'entrée invalide
                }
            }

            switch (choix) {
                case 1:
                    coupBase(cible);
                    break; // Attaque normale
                case 2:
                    doublelame(cible);
                    break; // Attaque puissante
                case 3:
                    pluiedague(cible);
                    break; // Combo spécial
            }
        } catch (Exception e) {
            System.out.println("Erreur de saisie. Veuillez entrer un nombre valide.");
        }
    }

    private void coupBase(Personnage cible) {
        System.out.println(nom + " effectue un coup de base sur " + cible.getnom());
        cible.recevoirdegat(degat);
    }

    private void doublelame(Personnage cible) {
        if (energie > 0) {
            System.out.println(nom + " effectue une attaque avec deux armes sur " + cible.getnom());
            cible.recevoirdegat(2 * degat);
        } else {
            System.out.println(nom + " n'a plus assez de energie pour cette attaque.");
        }

    }

    private void pluiedague(Personnage cible) {
        System.out.println(nom + " utilise l'attaque pluie de dague sur " + cible.getnom());
        cible.recevoirdegat(degat * 2);
        pointdevie -= 5;// Consomme un cœur ou un point d'énergie
        System.out.println("Coûts : -5 pv. point de vie restants : " + pointdevie);
    }

    public void utilisercompetence(Personnage cible) {

        System.out.println("\n--- Menu des Compétences Spéciales ---");
        System.out.println("1. des coup invisible de loin,-5 pv");
        System.out.println("2. une attaque de katana ");
        System.out.println("Choisissez une compétence : ");
        int choix = -1;
        try (Scanner scanner = new Scanner(System.in)) {
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    invisible(cible);
                    break;
                case 2:
                    katana(cible);
                    break;
                default:
                    System.out.println("Choix invalide, compétence annulée.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Erreur de saisie. Veuillez entrer un nombre valide.");
        }

    }

    private void invisible(Personnage cible) {
        System.out.println(nom + " utilise des coup invisible . La cible " + cible.getnom());
        this.gagnerExperience(20);
        pointdevie -= 5;
        cible.recevoirdegat(15 + degat);
    }

    private void katana(Personnage cible) {
        if (energie > 5) {
            System.out.println(nom + " utilise un katana .sur La cible " + cible.getnom());
            this.gagnerExperience(20);
            energie -= 5;
            cible.recevoirdegat(3 * degat);
        } else {
            System.out.println(nom + " n'a plus assez de energie pour cette attaque.");
        }

    }

    public void defance(Personnage cible) {
        System.out.println("\n--- Menu des Défenses ---");
        System.out.println("1. camouflage");
        System.out.println("2. pickpv");
        System.out.println("Choisissez une défense : ");
        try (Scanner scanner = new Scanner(System.in)) {
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    camouflage();
                    break;
                case 2:
                    pickpv(cible);
                    break;
                default:
                    System.out.println("Choix invalide, défense annulée.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Erreur de saisie. Veuillez entrer un nombre valide.");
        }
    }

    private void camouflage() {
        if (energie > 0) {
            System.out.println(nom + " se camoufle pour éviter les attaques ! ");
            energie--;
        } else {
            System.out.println(nom + " n'a pas assez d'énergie pour cette défense.");
        }
    }

    private void pickpv(Personnage cible) {
        if (energie > 0) {
            System.out.println(nom + " vole des PV à " + cible.getnom() + " pour se soigner !");
            int pvVolés = 10;
            cible.recevoirdegat(pvVolés);
            pointdevie = Math.min(pointdevie + pvVolés, 80); // Capacité maximale des PV = 80
            energie--;
            System.out.println("Coût : -1 énergie. Énergie restante : " + energie);
            System.out.println(nom + " récupère " + pvVolés + " PV. PV actuels : " + pointdevie);
        } else {
            System.out.println(nom + " n'a pas assez d'énergie pour utiliser Pick PV.");
        }
    }

    public void soin() {
        System.out.println(nom + " ne peut pas se soigner.");
    }

}

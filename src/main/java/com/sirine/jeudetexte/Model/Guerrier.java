package com.sirine.jeudetexte.Model;

import java.util.Scanner;

public class Guerrier extends Personnage {
    private int energie;

    public Guerrier(String nom, int pointsDeVieMax, int pointdevie, int degat, int energie) {
        super(nom, pointsDeVieMax, pointdevie, degat);
        this.energie = energie;
    }

    public Guerrier(String nom) {
        // Valeurs par défaut pour un mage
        super(nom, 100, 100, 15);
        this.energie = 5;
    }

    public void attaquer(Personnage cible) {
        System.out.println("\n--- Choisissez une attaque ---");
        System.out.println("1. Coup de base (10 dégâts)");
        System.out.println("2. Attaque puissante (20 dégâts, consomme de l'énergie)");
        System.out.println("3. Combo furieux (30 dégâts, mais réduit vos points de vie de 5)");
        System.out.println("4. Coup d'arme hache ghost 40 dégâts ");
        System.out.print("Choisissez une compétence : ");

        int choix = -1;
        try (Scanner scanner = new Scanner(System.in)) {
            while (choix < 1 || choix > 4) {
                if (scanner.hasNextInt()) {
                    choix = scanner.nextInt();
                    if (choix < 1 || choix > 4) {
                        System.out.println("Choix invalide, veuillez entrer un nombre entre 1 et 4.");
                    }
                } else {
                    System.out.println("Erreur de saisie. Veuillez entrer un nombre valide.");
                    scanner.next(); // consomme l'entrée invalide
                }
            }

            switch (choix) {
                case 1:
                    coupDeBase(cible);
                    break;
                case 2:
                    attaquePuissante(cible);
                    break;
                case 3:
                    comboFurieux(cible);
                    break;
                case 4:
                    hacheghost(cible);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Erreur de saisie. Veuillez entrer un nombre valide.");
        }
    }

    public void utilisercompetence(Personnage cible) {

        System.out.println("\n--- Menu des Compétences Spéciales ---");
        System.out.println("1. Cri de guerre ");
        System.out.println("2. Jeter une hache ghost améliorée");
        System.out.println("Choisissez une compétence : ");
        int choix = -1;
        try (Scanner scanner = new Scanner(System.in)) {
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    criDeGuerre(cible);
                    break;
                case 2:
                    hacheghostplus(cible);
                    break;
                default:
                    System.out.println("Choix invalide, compétence annulée.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Erreur de saisie. Veuillez entrer un nombre valide.");
        }
    }

    public void defance(Personnage cible) {

        System.out.println("\n--- Menu des Défenses ---");
        System.out.println("1. Blocage du bouclier");
        System.out.println("2. Posture défensive");
        System.out.println("3. Murre de terre (inverser l'attaque)");
        System.out.println("Choisissez une défense : ");
        int choix = -1;
        try (Scanner scanner = new Scanner(System.in)) {
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    blocageBouclier(cible);
                    break;
                case 2:
                    postureDefensive();
                    break;
                case 3:
                    murreterre(cible);
                    break;
                default:
                    System.out.println("Choix invalide, défense annulée.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Erreur de saisie. Veuillez entrer un nombre valide.");
        }
    }

    // Attaque 1 : Coup de base
    private void coupDeBase(Personnage cible) {
        System.out.println(nom + " effectue un coup de base sur " + cible.getnom());
        cible.recevoirdegat(degat);
    }

    // Attaque 2 : Attaque puissante
    private void attaquePuissante(Personnage cible) {
        if (energie > 0) {
            System.out.println(nom + " utilise une attaque puissante sur " + cible.getnom());
            cible.recevoirdegat(degat * 2);
            energie--; // Consomme un cœur ou un point d'énergie
            System.out.println("Coûts : -1 energie. energie restants : " + energie);
        } else {
            System.out.println(nom + " n'a plus assez de energie pour cette attaque.");
        }
    }

    // Attaque 3 : Combo furieux
    private void comboFurieux(Personnage cible) {
        System.out.println(nom + " utilise un combo furieux sur " + cible.getnom());
        cible.recevoirdegat(degat * 3);
        pointdevie -= 5; // Réduit les points de vie du joueur
        System.out.println("Effets secondaires : -5 PV. PV restants : " + pointdevie);
    }

    // Attaque 4 hache ghost:
    private void hacheghost(Personnage cible) {
        if (energie > 2) {
            System.out.println(nom + " utilise l'arme hache ghost sur " + cible.getnom());
            cible.recevoirdegat(degat * 4);
            energie -= 2;
            System.out.println("Coûts : -2 energie. energie restants : " + energie);
        } else {
            System.out.println(nom + " n'a plus assez de energie pour cette attaque.");
        }

    }

    // Compétences spécifiques
    private void criDeGuerre(Personnage cible) {
        System.out.println(nom + " utilise un cri de guerre. La cible " + cible.getnom() + " est intimidée !");
        this.gagnerExperience(20);
    }

    private void hacheghostplus(Personnage cible) {
        if (energie >= 4) {
            System.out.println(nom + " lance une hache ghost améliorée sur " + cible.getnom());
            cible.recevoirdegat(degat * 5);
            energie -= 4;
            System.out.println("Coût : -4 énergie. Énergie restante : " + energie);
        } else {
            System.out.println(nom + " n'a pas assez d'énergie pour cette compétence.");
        }
    }

    // Défenses spécifiques
    private void blocageBouclier(Personnage cible) {
        System.out.println(nom + " utilise un blocage du bouclier contre " + cible.getnom());
        cible.recevoirdegat(degat / 2);
        this.gagnerExperience(10);
    }

    private void postureDefensive() {
        if (energie > 0) {
            energie--;
            pointdevie -= 5;
            System.out.println(nom + " adopte une posture défensive, réduisant les dégâts du prochain coup.");
            System.out.println(
                    "Coût : -1 énergie, -5 PV. Énergie restante : " + energie + ", PV restants : " + pointdevie);
        } else {
            System.out.println(nom + " n'a pas assez d'énergie pour cette défense.");
        }
    }

    private void murreterre(Personnage cible) {
        if (energie > 0) {
            System.out.println(nom + " utilise un murre de terre pour contrer " + cible.getnom());
            cible.recevoirdegat(degat);
            this.gagnerExperience(15);
            energie--;
        } else {
            System.out.println(nom + " n'a pas assez d'énergie pour cette défense.");
        }
    }

    public void soin() {
        System.out.println(nom + " ne peut pas se soigner.");
    }
}

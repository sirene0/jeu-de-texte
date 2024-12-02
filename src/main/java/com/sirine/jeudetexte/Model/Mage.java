package com.sirine.jeudetexte.Model;

import java.util.Scanner;

public class Mage extends Personnage {
    private int energie;

    public Mage(String nom, int pointsDeVieMax, int pointdevie, int degat, int energie) {
        super(nom, pointsDeVieMax, pointdevie, degat);
        this.energie = energie;
    }

    public Mage(String nom) {
        // Valeurs par défaut pour un mage
        super(nom, 90, 90, 18);
        this.energie = 5;
    }

    public void attaquer(Personnage cible) {
        System.out.println("\n--- Choisissez une attaque ---");
        System.out.println("1. une sort elementaire de Feu 18 degats ");
        System.out.println("2. une sort elementaire de glace 18 degats");
        System.out.println("3. une sort elementaire de feu dragon 36 degat,mais réduit vos points de vie de 5");
        System.out.println("4. une sort elementaire de electricite(shidorii) feu feu 36 degat,2 energie");
        System.out.println("Choisissez une attaque : ");

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
                    Feu(cible);
                    break;
                case 2:
                    glace(cible);
                    break;
                case 3:
                    feudragon(cible);
                    break;
                case 4:
                    electricite(cible);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Erreur de saisie. Veuillez entrer un nombre valide.");
        }
    }

    private void Feu(Personnage cible) {
        System.out.println(nom + "utilise une sort basic de feu sur " + cible.getnom());
        cible.recevoirdegat(degat);
    }

    private void glace(Personnage cible) {
        System.out.println(nom + "utilise une sort basic de glace sur " + cible.getnom());
        cible.recevoirdegat(degat);
    }

    private void feudragon(Personnage cible) {
        System.out.println(nom + "utilise une sort feu de dragon sur " + cible.getnom());
        cible.recevoirdegat(2 * degat);
        pointdevie -= 5;
        System.out.println("Effets secondaires : -5 PV. PV restants : " + pointdevie);
    }

    private void electricite(Personnage cible) {
        if (energie > 2) {
            System.out.println(nom + "utilise une sort d'electricite sur " + cible.getnom());
            cible.recevoirdegat(2 * degat);
            energie -= 2;
            System.out.println("Coûts : -2 energie. energie restants : " + energie);
        } else {
            System.out.println(nom + " n'a plus assez de energie pour cette attaque.");
        }
    }

    public void utilisercompetence(Personnage cible) {
        // Menu pour choisir une attaque special
        System.out.println("\n--- Choisissez une attaque ---");
        System.out.println("1. Magie noire Manipulation de la gravite 36 degats ,3 energie ");
        System.out.println("2. Magie noire Flammes internales 45 degat ,10pv,3 energie");
        int choix = -1;
        try (Scanner scanner = new Scanner(System.in)) {
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    Manipulationgravite(cible);
                    break;
                case 2:
                    Flammesinternales(cible);
                    break;

                default:
                    System.out.println("Choix invalide, attaque annulée.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Erreur de saisie. Veuillez entrer un nombre valide.");
        }

    }

    private void Manipulationgravite(Personnage cible) {
        if (energie > 3) {
            System.out.println(nom + "utilise magie noire manipulation de  la gravite sur " + cible.getnom());
            cible.recevoirdegat(2 * degat);
            energie -= 3;
            System.out.println("Coûts : -3 energie. energie restants : " + energie);
        } else {
            System.out.println(nom + " n'a plus assez de energie pour cette attaque.");
        }
    }

    private void Flammesinternales(Personnage cible) {
        if (energie > 3) {
            System.out.println(nom + "utilise magie noire manipulation de  la gravite sur " + cible.getnom());
            cible.recevoirdegat(2 * degat + 9);
            energie -= 3;
            pointdevie -= 10;
            System.out.println("Coûts : -3 energie. energie restants : " + energie
                    + "Coûts : -10 point de vie. point de vie restant" + pointdevie);
        } else {
            System.out.println(nom + " n'a plus assez de energie pour cette attaque.");
        }
    }

    public void defance(Personnage cible) {

        System.out.println("\n--- Menu des Défenses ---");
        System.out.println("1. bouclier magique ");
        System.out.println("2. inverser l'attaque");
        System.out.println("3. camouflage murre de mana ");
        System.out.println("Choisissez une défense : ");
        try (Scanner scanner = new Scanner(System.in)) {
            int choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    boucliermagique(cible);
                    break;
                case 2:
                    inverser(cible);
                    break;
                case 3:
                    murremana(cible);
                    break;
                default:
                    System.out.println("Choix invalide, défense annulée.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Erreur de saisie. Veuillez entrer un nombre valide.");
        }
    }

    private void boucliermagique(Personnage cible) {
        System.out.println(nom + " utilise un bouclier magique contre " + cible.getnom());
        cible.recevoirdegat(degat / 2);
        this.gagnerExperience(10);
    }

    private void inverser(Personnage cible) {
        System.out.println(nom + " inverser l'attaque  contre " + cible.getnom());
        cible.recevoirdegat(degat);
        this.gagnerExperience(10);
    }

    private void murremana(Personnage cible) {
        if (energie > 0) {
            System.out.println(nom + " utilise un murre de mana  contrer " + cible.getnom());
            cible.recevoirdegat(degat);
            this.gagnerExperience(15);
            energie--;
        } else {
            System.out.println(nom + " n'a pas assez d'énergie pour cette défense.");
        }
    }

    // Méthode de soin
    public void soin() {
        if (energie >= 2) {
            int soin = 20; // Points de vie récupérés
            pointdevie = Math.min(90, pointdevie + soin); // Limite les PV au maximum initial
            energie -= 2; // Consomme de l'énergie
            System.out.println(nom + " se soigne et récupère " + soin + " points de vie.");
            System.out.println("PV actuels : " + pointdevie + ". Énergie restante : " + energie);
        } else {
            System.out.println(nom + " n'a pas assez d'énergie pour se soigner.");
        }
    }
}
